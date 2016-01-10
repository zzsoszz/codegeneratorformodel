package dinamica.http;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * A class encapsulating an HTTP query string.
 */
public final class QueryString implements Serializable
{
  private String mQueryString;
  private String mCharacterEncoding;
  private Map<String, List<Parameter>> mParameterMap;
  private List<Parameter> mParameterList;
  private List<String> mParameterNames;
  private String mFragment;

  /**
   * Construct a <code>QueryString</code> from a pre-encoded string.
   */
  public QueryString(String queryString, String characterEncoding)
  {
    // We only work on regular QueryStrings not strictXhtml QueryStrings
    mQueryString = queryString.replace("&amp;", "&");
    mCharacterEncoding = characterEncoding;
  }

  /**
   * Makes a copy of an existing <code>QueryString</code>.
   */
  public QueryString(QueryString source)
  {
    mQueryString = source.mQueryString;
    mCharacterEncoding = source.mCharacterEncoding;
    if (source.mParameterList != null)
    {
      mParameterList = new ArrayList<Parameter>(source.mParameterList);
    }
  }

  /**
   * Constructs an empty query string (parameters may be added later).
   */
  public QueryString(String characterEncoding)
  {
    mCharacterEncoding = characterEncoding;
  }

  /**
   * Constructs a query string from an old-fashioned array of PRE-ENCODED name-value pairs
   */
  public QueryString(String[][] args, String characterEncoding)
  {
    this(characterEncoding);
    for (String[] element : args)
    {
      addParameter(element[0], element[1], true);
    }
  }

  /**
   * Constructs a query string from a list of PRE-ENCODED name-value pairs
   */
  public QueryString(List<String[]> params, String characterEncoding)
  {
    this(characterEncoding);

    for (String[] pair : params)
    {
      // -= Simon Lessard =-
      // FIXME: Add if (pair == null) check
      addParameter(pair[0], pair[1], true);
    }
  }

  /**
   * Converts this object into an encoded query string.
   */
  @Override
  public String toString()
  {
    // Use appendTo to concatenate the parameters together
    if (mQueryString == null)
    {
      appendTo(new StringBuilder(200));
    }
    return mQueryString;
  }

  /**
   * Appends the contents of this object to the given buffer in encoded query string form.
   * 
   * @param buff
   *          the buffer to append to
   */
  public void appendTo(StringBuilder buff)
  {
    if (mQueryString == null)
    {
      // If we don't have a cached query string yet, generate it
      if (mParameterList == null || mParameterList.isEmpty())
      {
        // If we don't have any parameters at all, cache the empty string
        mQueryString = "";
      }
      else
      {
        // Remember the start position in the buffer, so that we can also
        // cache the concatenated string in mQueryString
        int startPos = buff.length();
        
        Iterator<Parameter> iter = mParameterList.iterator();
        Parameter param = iter.next();
        buff.append(param.getEncodedName()).append('=').append(param.getEncodedValue());
        
        while (iter.hasNext())
        {
          param = iter.next();
          buff.append('&').append(param.getEncodedName()).append('=')
            .append(param.getEncodedValue());
        }
          
        mQueryString = buff.substring(startPos);
      }
      
      if (mFragment != null)
      {
        mQueryString += mFragment;
      }
    }
    else
    {
      // If we have a cached query string, reuse it
      buff.append(mQueryString);
    }
  }

  public Enumeration<String> getParameterNames()
  {
    initParameterMap();
    return Collections.enumeration(mParameterNames);
  }
  
  public Map getParameterMap()
  {
    initParameterMap();
    return mParameterMap;
  }
  
  public int numParameters()
  {
    return (mParameterMap != null) ? mParameterMap.size() : 0;
  }

  public String getParameter(String name)
  {
    initParameterMap();
    List<Parameter> values = mParameterMap.get(name);
    return values == null ? null : values.get(0).getValue();
  }

  public Enumeration<String> getParameterValues(String name)
  {
    initParameterMap();
    List<Parameter> params = mParameterMap.get(name);
    if (params == null || params.isEmpty())
    {
      List<String> temp = Collections.emptyList();
      return Collections.enumeration(temp);
    }
    
    List<String> values = new ArrayList<String>(params.size());
    for (Parameter param : params)
    {
      values.add(param.getValue());
    }
    
    return Collections.enumeration(values);
  }

  public void addParameter(String name, String value)
  {
    addParameter(name, value, false);
  }

  public void addParameter(String name, String value, boolean isEncoded)
  {
    if (value == null)
    {
      return;
    }
    initParameterList();

    // Invalidate the query string
    mQueryString = null;

    // Update the parameter list
    Parameter param = new Parameter(name, value, isEncoded);
    mParameterList.add(param);

    // Update the parameter map if it is initialized
    if (mParameterMap != null)
    {
      String decodedName = param.getName();
      List<Parameter> values = mParameterMap.get(decodedName);
      if (values == null)
      {
        createParameterList(param);
      }
      else
      {
        values.add(param);
      }
    }
  }

  public void setParameter(String name, String value)
  {
    setParameter(name, value, false);
  }

  public void setParameter(String name, String value, boolean isEncoded)
  {
    if (value == null)
    {
      removeParameter(name, isEncoded);
      return;
    }
    initParameterMap();

    // Invalidate the query string
    mQueryString = null;

    // Update the map
    Parameter param = new Parameter(name, value, isEncoded);
    String decodedName = param.getName();
    List<Parameter> values = mParameterMap.get(decodedName);
    if (values == null)
    {
      createParameterList(param);
      mParameterList.add(param);
    }
    else
    {
      values.clear();

      // First, replace the existing occurence of the parameter
      int i = mParameterList.indexOf(param);
      mParameterList.set(i, param);

      // Now, remove any subsequent occurrences
      int j;
      while ((j = mParameterList.lastIndexOf(param)) > i)
      {
        mParameterList.remove(j);
      }
      
      values.add(param);
    }
  }

  public String removeParameter(String name)
  {
    return removeParameter(name, false);
  }

  public String removeParameter(String name, boolean isEncoded)
  {
    initParameterList();

    // Invalidate the query string
    mQueryString = null;

    // Create a template parameter for comparisons, so that we can avoid
    // decoding all parameter names in the list
    Parameter templateParam = new Parameter(name, "", isEncoded);

    // Update the parameter list
    Iterator<Parameter> i = mParameterList.iterator();
    Parameter firstParam = null;
    while (i.hasNext())
    {
      Parameter param = i.next();
      // Compare the parameter with our template (only the template name
      // will be encoded / decoded if necessary)
      if (templateParam.equals(param))
      {
        if (firstParam == null)
        {
          firstParam = param;
        }
        
        i.remove();
      }
    }

    if (firstParam == null)
    {
      return null;
    }

    // Update the map, if it is initialized and we found a parameter
    if (mParameterMap != null)
    {
      String decodedName = templateParam.getName();
      List<Parameter> values = mParameterMap.remove(decodedName);
      if (values != null)
      {
        mParameterNames.remove(decodedName);
      }
    }

    return isEncoded ? firstParam.getEncodedValue() : firstParam.getValue();
  }
  
  private void createParameterList(Parameter param)
  {
    String decodedName = param.getName();
    
    List<Parameter> values = new ArrayList<Parameter>(4);
    mParameterMap.put(decodedName, values);
    
    // Only add UNIQUE parameter names (preserving order)
    mParameterNames.add(decodedName);
    
    values.add(param);
  }

  private void initParameterMap()
  {
    if (mParameterMap == null)
    {
      initParameterList();

      // TODO: Constants
      mParameterMap = new HashMap<String, List<Parameter>>(30);
      mParameterNames = new ArrayList<String>(30);
      if (mParameterList.isEmpty())
      {
        return;
      }
      
      String decodedName;
      
      for (Parameter param : mParameterList)
      {
        decodedName = param.getName();
        List<Parameter> values = mParameterMap.get(decodedName);
        if (values == null)
        {
          createParameterList(param);
        }
        else
        {
          values.add(param);
        }
      }
    }
  }

  private void initParameterList()
  {
    if (mParameterList == null)
    {
      // remove/deal with a fragment hanging on the end
      initFragment();
      
      // TODO: Constant
      mParameterList = new ArrayList<Parameter>(30);
      int length;
      if (mQueryString == null || (length = mQueryString.length()) == 0)
      {
        return;
      }
      Parameter param;
      int lastPos = 0, nextPos, sepPos;
      do
      {
        nextPos = mQueryString.indexOf('&', lastPos);
        if (nextPos == -1)
        {
          nextPos = length;
        }
        sepPos = mQueryString.indexOf('=', lastPos);
        if (sepPos != -1 && sepPos < nextPos)
        {
          param = new Parameter(mQueryString.substring(lastPos, sepPos),
                                mQueryString.substring(sepPos + 1, nextPos), true);
        }
        else
        {
          param = new Parameter(mQueryString.substring(lastPos, nextPos), "", true);
        }
        mParameterList.add(param);
        lastPos = nextPos + 1;
      } while (nextPos < length);
    }
  }
  
  private void initFragment()
  {
    if (mQueryString != null)
    {
      int i = mQueryString.indexOf('#');
      if (i == 0)
      {
        mFragment = mQueryString;
        mQueryString = "";
      }
      else if (i > 0)
      {
        mFragment = mQueryString.substring(i);
        mQueryString = mQueryString.substring(0, i);
      }
    }
  }

  private class Parameter implements Serializable
  {
    private String mName;
    private String mEncodedName;

    private String mValue;
    private String mEncodedValue;
    
    public Parameter(String name, String value, boolean encoded)
    {
	      if (encoded)
	      {
	        mEncodedName = name;
	        mEncodedValue = value;
	      }
	      else
	      {
	        mName = name;
	        mValue = value;
	      }
	    }
	
	    public String getName()
	    {
	      if (mName == null)
	      {
	        try
	        {
	          mName = URLDecoder.decode(mEncodedName, mCharacterEncoding);
	        }
	        catch (UnsupportedEncodingException uee)
	        {
	          handleUnsupportedEncoding();
	        }
	      }
	      return mName;
	    }
	
	    public String getEncodedName()
	    {
	      if (mEncodedName == null)
	      {
	        try
	        {
	          mEncodedName = URLEncoder.encode(mName, mCharacterEncoding);
	        }
	        catch (UnsupportedEncodingException uee)
	        {
	          handleUnsupportedEncoding();
	        }
	      }
	      return mEncodedName;
	    }
	
	    public String getValue()
	    {
	      if (mValue == null)
	      {
	        try
	        {
	          mValue = URLEncoder.encode(mEncodedValue, mCharacterEncoding);
	        }
	        catch (UnsupportedEncodingException uee)
	        {
	          handleUnsupportedEncoding();
	        }
	      }
	      return mValue;
	    }
	
	    public String getEncodedValue()
	    {
	      if (mEncodedValue == null)
	      {
	        try
	        {
	          mEncodedValue = URLEncoder.encode(mValue, mCharacterEncoding);
	        }
	        catch (UnsupportedEncodingException uee)
	        {
	          handleUnsupportedEncoding();
	        }
	      }
	      return mEncodedValue;
	    }
	
	    /**
	     * Compares two parameters for name equality.
	     * 
	     * Attempts not to invoke any lazy encoding or decoding in the passed in parameter - only in
	     * this one.
	     */
	    @Override
	    public boolean equals(Object o)
	    {
	      if (o == null || !(o instanceof Parameter))
	      {
	        return false;
	      }
	      Parameter p1 = (Parameter) o;
	      return p1.mName != null && getName().equals(p1.mName) || p1.mEncodedName != null
	             && getEncodedName().equals(p1.mEncodedName);
	    }
  }

  private void handleUnsupportedEncoding()
  {
    throw new IllegalArgumentException(
           new StringBuilder(100)
           .append( "Unrecognized character encoding \"")
           .append(mCharacterEncoding)
           .append('"').toString());
  }
  
  
	public static void main(String[] args) throws URISyntaxException {
		QueryString q=new QueryString("/forum/article.jsp?id=2");
		q.addParameter("user", "fff");
		//System.out.println(q.toString());
	}
}

