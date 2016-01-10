package dinamica.util;


/*
 * httpclient 4.3.3
 * http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/index.html
 * http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/connmgmt.html#d5e363
 */
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinamica.guid.Guid;

/*
 * HttpClient 4.3中Https的使用方法
 * http://www.nitrohsu.com/http-client-4-3-zhong-https-de-shi-yong-fang-fa.html
 */

public class HttpHelper    
{      

	private static final Log logger = LogFactory.getLog(HttpHelper.class);
	
	public  static void main(String args[]) throws Exception
	{
//		System.out.println(WeiXinPayClient.class.getResource("apiclient_cert.p12").getPath());
//		KeyStore trustStore=CertHeper.readP12(WeiXinPayClient.class.getResource("apiclient_cert.p12").getPath(),Configure.mchID);
//      	
		
		
//		System.out.println(dinamica.util.URLEncoder.encode("http://www.ly.com/flight/flight-neBookPort.aspx?type=1&goguid=75791c5b-ff65-4f29-b706-72f052e05554&goflightno=KN5218&gocabincode=T&gofzproductid=133&KeyCode=9fef9b49d37695115ba64c762c014a73&refid=26875429"));;
//		HashMap resp = HttpHelper.sendBySavedFile("/logs/362687476600410112.req.txt");
//		System.out.println(resp.get("data"));;
		//String json="";
		
//		System.out.println(HttpHelper.sendJson("http://www.baidu.com", "{}", "192.168.1.116", 808));;
//		String md5=HttpHelper.getFileMd5("http://tcopenapi.17usoft.com/static/xml/hotel/hotellist.xml");
//		//logger.debug(md5);
		//eefb6287476bacca36fb7ee5d10b2009
		//eefb6287476bacca36fb7ee5d10b2009
//		
//		URL url = new URL("http://tcopenapi.17usoft.com/static/xml/hotel/hotellist.xml");
//		InputStream is = url.openStream();
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		String digest = getDigest(is, md, 2048);
//		//logger.debug("MD5 Digest:" + digest);
//		
		//108bef2b3d9c7673477b9ecce368a688
		
		
		//String file=TongchengImp.class.getResource(".").getFile();
//		//copy(new FileInputStream(file),new FileOutputStream("c:/aa.xml"),1024);
//		FileHelper.saveStreamToFile(new FileInputStream(file),new File("c:/aa.xml"));
//		//logger.debug(file);
//		//logger.debug(FileHelper.getDigest(new File(file)));
		////logger.debug(file);
		//downloadAndSaveToFile("http://tcopenapi.17usoft.com/static/xml/hotel/hotellist.xml",new File("c:/hotellist.xml"));
		//HttpHelper433.sendPost("http:
		//www.baidu.com", null);
		//ExecutorService pool=Executors.newFixedThreadPool(1000);
//		for(int i=0;i<10;i++)
//		{
//			DealThread dh=new DealThread();
//			new Thread(dh).start();
//			//pool.execute(dh);
//		}
		
//		HashMap str=HttpHelper.sendGet("https://api.test.lohoo.com/rest?format=json&method=hotel.order.create&user=Agent1419328190&timestamp=1431328165&signature=be70ef82a8ef1d4ea0c6fa0467bc85a7&data=%7B%22Local%22%3A%22zh_CN%22%2C%22Request%22%3A%7B%22AffiliateConfirmationId%22%3A%22my-order-id-2%22%2C%22ArrivalDate%22%3A%222015-05-12+15%3A09%3A24%22%2C%22ConfirmationType%22%3A%22NotAllowedConfirm%22%2C%22Contact%22%3A%7B%22Mobile%22%3A%2218600000001%22%2C%22Name%22%3A%22Ms+White%22%7D%2C%22CreditCard%22%3A%7B%22ExpirationMonth%22%3A10%2C%22ExpirationYear%22%3A2016%2C%22HolderName%22%3A%22de97d8227b8c677bb1638ef3d3ff8bf8cbe5423dcb32294f4dde2b6367797f34%22%2C%22IdNo%22%3A%22de97d8227b8c677b113f0a514c2f9cb6359ee1af0ce5d40b8f2e0e4097ca9c36%22%2C%22IdType%22%3A%22IdentityCard%22%2C%22Number%22%3A%22de97d8227b8c677b799367a17d86b7465442d788c76f026c60956bc89edb80d2%22%2C%22cVV%22%3A%22de97d8227b8c677b89dbc57a8038617e%22%7D%2C%22CurrencyCode%22%3A%22HKD%22%2C%22CustomerIPAddress%22%3A%22211.151.230.21%22%2C%22CustomerType%22%3A%22OtherForeign%22%2C%22DepartureDate%22%3A%222015-05-13+15%3A09%3A24%22%2C%22EarliestArrivalTime%22%3A%222015-05-12+15%3A00%3A00%22%2C%22HotelId%22%3A%2210101129%22%2C%22IsForceGuarantee%22%3Afalse%2C%22IsGuaranteeOrCharged%22%3Afalse%2C%22IsNeedInvoice%22%3Afalse%2C%22IsNewPaymentFlow%22%3Afalse%2C%22LatestArrivalTime%22%3A%222015-05-12+17%3A00%3A00%22%2C%22NoteToElong%22%3A%22%22%2C%22NumberOfCustomers%22%3A1%2C%22NumberOfRooms%22%3A1%2C%22OrderRooms%22%3A%5B%7B%22Customers%22%3A%5B%7B%22Name%22%3A%22Jack+White%22%7D%5D%7D%5D%2C%22PaymentType%22%3A%22SelfPay%22%2C%22RatePlanId%22%3A145742%2C%22RoomTypeId%22%3A%220010%22%2C%22TotalPrice%22%3A600%7D%2C%22Version%22%3A1.1%7D", null, "119.254.84.180", 8888);
//		System.out.println(str.get("returncode"));
//		System.out.println(str.get("returnmsg"));
//		System.out.println(str.get("data"));
		
		
		HashMap ss = HttpHelper.sendGet("http://www.baidu.com","119.254.84.180",8777);
		System.out.println(ss);
	}
	
	
	
	static CloseableHttpClient  httpClient;
	static int connectTimeout=60000;
	static int socketTimeout=60000;
	  org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
	    
	static {
		//http://hadoop.cf/visit-https-without-auth/
		//http://blog.csdn.net/chaijunkun/article/details/40145685
		//http://blog.csdn.net/trbbadboy/article/details/11562511
		
		
		
       Registry<ConnectionSocketFactory> socketFactoryRegistry=null;
       try {
    	   
    	   
    	   X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
			@Override
			public void verify(String arg0, SSLSocket arg1) throws IOException {
				
			}
			@Override
			public void verify(String arg0, X509Certificate arg1)
					throws SSLException {
			}
			@Override
			public void verify(String arg0, String[] arg1, String[] arg2)
					throws SSLException {
			}
           };
           
           
         //实现X509TrustManager接口，如下三个方法为必须实现，将其实现为什么都不做
           X509TrustManager tm = new X509TrustManager() {
              public void checkClientTrusted(X509Certificate[] xcs, String string) {
              }

              public void checkServerTrusted(X509Certificate[] xcs, String string) {
              }

              public X509Certificate[] getAcceptedIssuers() {
                  return null;
              }
          };
          
          
//          	KeyStore trustStore=CertHeper.readP12(WeiXinPayClient.class.getResource("apiclient_cert.p12").getPath(),Configure.mchID);
//            SSLContext sslcontext = SSLContexts.custom()
//                    .loadKeyMaterial(trustStore, Configure.mchID.toCharArray())
//                    .build();
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext,
//                    new String[] { "TLSv1" },
//                    null,
//                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//            
//            
	       	socketFactoryRegistry = RegistryBuilder
	                .<ConnectionSocketFactory> create()
	                .register("http", PlainConnectionSocketFactory.INSTANCE)
//	                .register("https", sslsf)
	                .build();
		}catch (Exception e) {
			e.printStackTrace();
		}
        
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
//		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
//		HttpHost localhost = new HttpHost("locahost", 80);
//		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		
		
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
		    public boolean retryRequest(
		            IOException exception,
		            int executionCount,
		            HttpContext context) {
		        if (executionCount >= 5) {
		            // Do not retry if over max retry count
		        	//// 如果超过最大重试次数，那么就不要继续了 
		            return false;
		        }
		        if (exception instanceof java.io.InterruptedIOException) {
		            // Timeout
		            return false;
		        }
		        if (exception instanceof UnknownHostException) {
		            // Unknown host
		            return false;
		        }
		    	if (exception instanceof NoHttpResponseException) { 
		    		// // 如果服务器丢掉了连接，那么就重试 
		    		return true; 
		    	} 
		        if (exception instanceof ConnectTimeoutException) {
		            // Connection refused
		            return false;
		        }
		        if (exception instanceof SSLException) {
		            // // 不要重试SSL握手异常 
		            return false;
		        }
		        HttpClientContext clientContext = HttpClientContext.adapt(context);
		        HttpRequest request = clientContext.getRequest();
		        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
		        if (idempotent) {
		            // Retry if the request is considered idempotent
		        	//// 如果请求被认为是幂等的，那么就重试 
		            return true;
		        }
		        return false;
		    }

		};
		
		
		
//		// Create a local instance of cookie store
//		CookieStore cookieStore = new BasicCookieStore();
//		// Populate cookies if needed
//		BasicClientCookie cookie = new BasicClientCookie("name", "value");
//		cookie.setVersion(0);
//		cookie.setDomain(".mycompany.com");
//		cookie.setPath("/");
//		cookieStore.addCookie(cookie);
//		// Set the store
//		CloseableHttpClient httpclient = HttpClients.custom()
//		        .setDefaultCookieStore(cookieStore)
//		        .build();
//		
		
		
//		
//		MessageConstraints messageConstraints = MessageConstraints.custom()
//	            .setMaxHeaderCount(200)
//	            .setMaxLineLength(2000)
//	            .build();
//		
		
		
		
		
        
		
		 ConnectionConfig connectionConfig = ConnectionConfig.custom()
		            .setMalformedInputAction(CodingErrorAction.IGNORE)
		            .setUnmappableInputAction(CodingErrorAction.IGNORE)
		            .setCharset(Consts.UTF_8)
		         // .setMessageConstraints(messageConstraints)
		            .build();
		 
		 
		 cm.setDefaultConnectionConfig(connectionConfig);
		 
//		 
//		org.apache.http.client.config.RequestConfig;
//		org.apache.http.config.ConnectionConfig;
		 
//		 
//		 KeyStore myTrustStore =null;
//		 SSLContext sslContext;
//		 try {
//			sslContext = SSLContexts.custom()
//			         .useTLS()
//			         .loadTrustMaterial(myTrustStore)
//			         .build();
//			 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
//			 Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
//				        .register("http", plainsf)
//				        .register("https", sslsf)
//				        .build();
//	 	 } catch (KeyManagementException e) {
//			e.printStackTrace();
//	  	 } catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		 } catch (KeyStoreException e) {
//			e.printStackTrace();
//		 }
//		 
		 
//	      org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
//        cookieStore.addCookie(new BasicClientCookie("BDUSS", "VpBeHNMMUVuQ0o3c2pXZ0tQYXB3SWpjTG1rZTVvQzZ3OHRaTHpDTkZVSm00RkpSQVFBQUFBJCQAAAAAAAAAAAEAAABtnVkYyvq-ocC0vdkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGZTK1FmUytRR"));
//        cookieStore.addCookie(new BasicClientCookie("BAIDUID", "1BE2294325A5959BFC652195D4C6754F:FG=1"));
//        cookieStore.addCookie(new BasicClientCookie("BAIDU_WISE_UID", "EAC7E7B67B6423D0053B993B87A7DC2A"));
//        
//		  HttpClientBuilder builder = HttpClients.custom().setDefaultCookieStore(cookieStore);
		
		 	
		  
			RequestConfig globalConfig = RequestConfig.custom()
			        .setCookieSpec(CookieSpecs.BEST_MATCH)
			        .build();
			RequestConfig localConfig = RequestConfig.copy(globalConfig)
			        .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
			        .build();
			
			
//			RequestConfig globalConfig = RequestConfig.custom()
//			        .setCookieSpec(CookieSpecs.BEST_MATCH)
//			        .build();
//			CloseableHttpClient httpclient = HttpClients.custom()
//			        .setDefaultRequestConfig(globalConfig)
//			        .build();
//			RequestConfig localConfig = RequestConfig.copy(globalConfig)
//			        .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
//			        .build();
//			HttpGet httpGet = new HttpGet("/");
//			httpGet.setConfig(localConfig);
			
			
			//HttpClientBuilder builder = HttpClients.custom().setDefaultRequestConfig(localConfig).setDefaultCookieStore(cookieStore);;
			//HttpClientBuilder builder = HttpClients.custom().setDefaultRequestConfig(localConfig).setDefaultCookieStore(cookieStore);;
			
			HttpClientBuilder builder = HttpClients.custom().setDefaultRequestConfig(localConfig);//.setDefaultCookieStore(cookieStore);;
			
			
//	httpClient = HttpAsyncClients.custom().build();
//	CloseableHttpAsyncClient asyclient=HttpAsyncClients.custom().build();
//		 CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
//	        try {
//	            httpclient.start();
//	            HttpGet request = new HttpGet("http://www.apache.org/");
//	            Future<HttpResponse> future = httpclient.execute(request, null);
//	            HttpResponse response = future.get();
//	            System.out.println("Response: " + response.getStatusLine());
//	            System.out.println("Shutting down");
//	        } finally {
//	            httpclient.close();
//	        }
//	        System.out.println("Done");
		
		
		
//		//ssl
//	    KeyStore trustStore;
//		try {
//			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			
//			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
//			try {
//				// 加载keyStore d:\\tomcat.keystore  
//				trustStore.load(instream, "123456".toCharArray());
//			} catch (CertificateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					instream.close();
//				} catch (Exception ignore) {
//				}
//			}
//			
//			// 相信自己的CA和所有自签名的证书
//			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
//			// 只允许使用TLSv1协议
//			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//			builder.setSSLSocketFactory(sslsf);
//		} catch (KeyStoreException e1) {
//			e1.printStackTrace();
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		


        
		httpClient = builder.setConnectionManager(cm).setRetryHandler(myRetryHandler).build();
		
		
		
//		@SuppressWarnings("deprecation")
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		// 创建一个本地的cookie store实例
//
//		CookieStore cookieStore = new MyCookieStore();
//
//		// 如果需要填充cookie
//
//		BasicClientCookie cookie = new BasicClientCookie("name", "value");
//
//		cookie.setVersion(0);
//
//		cookie.setDomain(".mycompany.com");
//
//		cookie.setPath("/");
//
//		cookieStore.addCookie(cookie);
//
//		// 设置存储
//
//		httpclient.setCookieStore(cookieStore);
//		
		//new IdleConnectionMonitorThread(cm).start();
	}
	
	
	
	
//
//	public static class IdleConnectionMonitorThread extends Thread {
//	    
//	    private final HttpClientConnectionManager connMgr;
//	    private volatile boolean shutdown;
//	    
//	    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
//	        super();
//	        this.connMgr = connMgr;
//	    }
//
//	    @Override
//	    public void run() {
//	        try {
//	            while (!shutdown) {
//	                synchronized (this) {
//	                    wait(5000);
//	                    // Close expired connections
//	                    connMgr.closeExpiredConnections();
//	                    // Optionally, close connections
//	                    // that have been idle longer than 30 sec
//	                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
//	                }
//	            }
//	        } catch (InterruptedException ex) {
//	            // terminate
//	        }
//	    }
//	    
//	    public void shutdown() {
//	        shutdown = true;
//	        synchronized (this) {
//	            notifyAll();
//	        }
//	    }
//	    
//	}


	
	public static Map<String, String> getQueryMap(String query)
	{
	    String[] params = query.split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params)
	    {
	        String name = param.split("=")[0];
	        String value = param.split("=")[1];
	        map.put(name, value);
	    }
	    return map;
	}
//
//	public static class IdleConnectionMonitorThread extends Thread {
//	    
//	    private final ClientConnectionManager connMgr;
//	    private volatile boolean shutdown=false;
//	    
//	    public IdleConnectionMonitorThread(ClientConnectionManager connMgr) {
//	        super();
//	        this.connMgr = connMgr;
//	    }
//	
//	    @Override
//	    public void run() {
//	        try {
//	            while (!shutdown) {
//	                synchronized (this) {
//	                    wait(5000);
//	                    ////logger.debug("关闭空闲http连接");
//	                    // Close expired connections
//	                    connMgr.closeExpiredConnections();
//	                    // Optionally, close connections
//	                    // that have been idle longer than 30 sec
//	                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
//	                }
//	            }
//	        } catch (InterruptedException ex) {
//	            // terminate
//	        }
//	    }
//	    
//	    public void shutdown() {
//	        shutdown = true;
//	        synchronized (this) {
//	            notifyAll();
//	        }
//	    }
//	    
//	}
//	
	
	/*
	 * 
HttpClient 4.3超时设置  
http://www.open-open.com/lib/view/open1383751765321.html
您的评价: 	
还行
	
 收藏该经验    

HttpClient 4.3。HttpClient这货和Lucene一样，每个版本的API都变化很大，这有点让人头疼。就好比创建一个HttpClient对象吧，每一个版本的都不一样，

3.X是这样的
1	HttpClient httpClient=new DefaultHttpClient();
4.3是这样的
1	CloseableHttpClient httpClient = HttpClients.createDefault();
当然，上面这些变化只不过是一些小变化，大家看看API大家就都会了。

我要讲的是超时设置,HttpClient有三种超时设置，最近比较忙，没时间具体归纳总结，以后再补上，我这里就讲一些最简单最易用的超时设置方法。

这是个3.X的超时设置方法
1	HttpClient client = new HttpClient();
2	client.setConnectionTimeout(30000); 
3	client.setTimeout(30000);
1	HttpClient httpClient= new HttpClient(); 
2	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
4.X版本的超时设置(4.3后已过时)
1	HttpClient httpClient=new DefaultHttpClient();
2	httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);//连接时间
3	httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);//数据传输时间
4.3版本超时设置
1	CloseableHttpClient httpClient = HttpClients.createDefault();
2	HttpGet httpGet=new HttpGet("http://www.baidu.com");//HTTP Get请求(POST雷同)
3	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
4	httpGet.setConfig(requestConfig);
5	httpClient.execute(httpGet);//执行请求
BTW,4.3版本不设置超时的话，一旦服务器没有响应，等待时间N久(>24小时)。 
	 */
	

	
	
	public static String convertHashMapToURL(String path,Map<String, String> params, String enc) throws UnsupportedEncodingException 
    {
        StringBuilder sb = new StringBuilder(path);   
        if(params!=null && params.size()>0 )
        {
        	sb.append('?');
        	for(Map.Entry<String, String> entry : params.entrySet())   
	        {   
	        		 sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), enc)).append('&');   
	        }
        	sb.deleteCharAt(sb.length()-1);   
        }
		return sb.toString();
    }
	public static HashMap sendGet(String path) 
    {
		return sendGet( path, new HashMap(), new HashMap(),"UTF-8");
    }
	public static HashMap sendGet(String path, Map<String, String> params, String enc) 
    {
		return sendGet( path, new HashMap(),params,enc);
    }
	public static HashMap sendGet(String path, String proxyhost,int proxyport) 
    {
		return sendGet( path, new HashMap(),new HashMap(),"UTF-8",proxyhost,proxyport);
    }
	public static HashMap sendGet(String path, Map<String, String> params, String proxyhost,int proxyport) 
    {
		return sendGet( path, new HashMap(),params,"UTF-8",proxyhost,proxyport);
    }
	public static HashMap sendGet(String path, Map<String, String> headers,Map<String, String> params, String enc) 
	{
		return sendGet( path,  headers, params,  enc, null, 0) ;
	}
	public static HashMap sendGet(String path,Map<String, String> params, String enc,String proxyhost,int proxyport) 
	{
		return sendGet( path,  new HashMap(), params,  enc, proxyhost,proxyport) ;
	}
    public static HashMap sendGet(String path, Map<String, String> headers,Map<String, String> params, String enc,String proxyhost,int proxyport) 
    {
    	HashMap map=new HashMap();
    	HttpGet method=null;
    	CloseableHttpResponse response =null;
		try{
	        StringBuilder sb = new StringBuilder(path);   
	        
	        if(params!=null && params.size()>0 )
	        {
	        	sb.append('?');
	        	for(Map.Entry<String, String> entry : params.entrySet())   
		        {   
		        		 sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), enc)).append('&');   
		        }
	        	sb.deleteCharAt(sb.length()-1);   
	        }
	        
	        System.out.println("sendGet:"+sb.toString());
	        map.put("returncode", "11111111");
	        map.put("returnmsg", "");
	        map.put("data", "");
	        map.put("url",sb.toString());
	        method = new HttpGet(sb.toString());
	        
	        
	        if(headers!=null)
	        {
	        	  Header[] hds=new Header[headers.size()];
	  	        int i=0;
	  	        for(String key:headers.keySet())
	  	        {
	  	        	hds[i++]=new BasicHeader(key,headers.get(key));
	  	        }
	  			method.setHeaders(hds);
	        }
	      
			
			HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyhost!=null && proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	//method.setConfig(requestConfig);
			//WebClientDevWrapper.wrapClient(httpClient);
	    	response= httpClient.execute( method, context);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String dc  = Charset.defaultCharset().name();  
				HttpEntity entity = response.getEntity();
				BufferedReader br = new BufferedReader(new UnicodeReader(entity.getContent(),enc));    
				String str = IOUtils.toString(br);
				EntityUtils.consume(entity);
				map.put("data", str);
				map.put("returncode", "00000000");
				return map;
			} else {
				map.put("returncode", "00000001");
				map.put("returnmsg", ""+response.getStatusLine());
				return map;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			map.put("returncode", "00000001");
			map.put("returnmsg", ex.getMessage());
		}finally 
		{
			if(method!=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
    }
    
    
    
    
    public static InputStream sendGetForInputStream(String path,Map<String, String> params,String enc) throws ClientProtocolException, IOException 
    {
    	HashMap map=new HashMap();
    	HttpGet method=null;
    	CloseableHttpResponse response =null;
        StringBuilder sb = new StringBuilder(path);   
        
        if(params!=null && params.size()>0 )
        {
        	sb.append('?');
        	for(Map.Entry<String, String> entry : params.entrySet())   
	        {   
	        		 sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), enc)).append('&');   
	        }
        	sb.deleteCharAt(sb.length()-1);   
        }
        
        System.out.println("sendGet:"+sb.toString());
        map.put("returncode", "11111111");
        map.put("returnmsg", "");
        map.put("data", "");
        map.put("url",sb.toString());
        method = new HttpGet(sb.toString());
        
		
		HttpClientContext  context = HttpClientContext.create();
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    	method.setConfig(requestConfig);
    	
    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    	//method.setConfig(requestConfig);
		//WebClientDevWrapper.wrapClient(httpClient);
    	response= httpClient.execute( method, context);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String dc  = Charset.defaultCharset().name();  
			HttpEntity entity = response.getEntity();
			return entity.getContent();
		}
		return null;
    }
    
    
    public static HashMap sendPost(String path,Map<String,String>  params,String enc)   
    {
    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
    	for(String key:params.keySet())
    	{
    		String value=params.get(key);
    		String[] valuearray=new String[]{value};
			paramresult.put(key, valuearray);
    	}
    	return sendPost(path,null,paramresult,enc,null,0);
    }
    public static HashMap sendPost(String path,Map<String,String>  headers, Map<String, String> params, String enc)   
    {
    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
    	for(String key:params.keySet())
    	{
    		String value=params.get(key);
    		String[] valuearray=new String[]{value};
			paramresult.put(key, valuearray);
    	}
    	return sendPost(path,headers,paramresult,enc,null,0);
    }
    public static HashMap sendPost(String path,Map<String,String>  params,String enc,String proxyhost,int proxyport)   
    {
    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
    	for(String key:params.keySet())
    	{
    		Object value=params.get(key);
    		String[] valuearray=new String[]{value.toString()};
			paramresult.put(key, valuearray);
    	}
    	return sendPost(path,null,paramresult,enc,proxyhost,proxyport);
    }
    public static HashMap sendPost(String path,Map<String,String>  headers,InputStream is)
    {
    	return sendPost(path,headers,is,null,0);
    }
    public static HashMap sendPost(String path,Map<String, String> headers,InputStream is,String proxyhost,int proxyport)   
    {
    	HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
        map.put("url", path);
        HttpPost method =null;
        CloseableHttpResponse response=null;
        try 
		{
        	InputStreamEntity httpentity = new InputStreamEntity(is, is.available());  
	        method = new HttpPost(path);
	        if(headers.size()>0)
	        {
	        	Header[] hds=new Header[]{};
	 	        int i=0;
	 	        for(String key:headers.keySet())
	 	        {
	 	        	hds[i++]=new BasicHeader(key,headers.get(key));
	 	        }
	 			method.setHeaders(hds);
	        }
	        HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyhost!=null && proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	method.setEntity(httpentity);
	        
			WebClientDevWrapper.wrapClient(httpClient);
			response = httpClient.execute( method, context);
			try{
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String str = EntityUtils.toString(response.getEntity());
					map.put("data", str);
					map.put("returncode", "00000000");
					return map;
				} else {
					map.put("returncode", "00000001");
					map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString() );
					return map;
				}
			}catch(Exception ex)
			{
				map.put("returncode", "00000001");
				map.put("returnmsg", ex.getMessage());
			}finally 
			{
				if(null!= method)    
				{
					method.releaseConnection();    
				}
				response.close();
			}
			
			
		} catch (ClientProtocolException e) {
			map.put("returnmsg", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			map.put("returnmsg", e.getMessage());
			e.printStackTrace();
		} finally 
		{
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return map;   
    }
    
    

    
    public static HashMap sendPost(String path, Map<String, String> headers, Map<String, String[]> params)   
    {
    	return sendPost( path, headers, params, "UTF-8", null,0);
    }
    public static HashMap sendPost(String path, Map<String, String> headers, Map<String, String[]> params, String enc,String proxyhost,int proxyport)   
    {
    	HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
        map.put("url",path);
        
        
        HttpPost method =null;
        CloseableHttpResponse response=null;
        try 
		{
	        List<NameValuePair> formParams = new ArrayList<NameValuePair>();   
	        if(params!=null)
	        {
	        	for(String key: params.keySet())   
		        {
	        		 Object value=params.get(key);
	        		 if(value instanceof   String[] )
	        		 {
	        			 String valuearray[]=(String[])value;
	        			 if(valuearray.length==1)
	        			 {
	        				 formParams.add(new BasicNameValuePair(key,valuearray[0])); 
	        			 }else{
	        				 for(int i=0;i<valuearray.length;i++)
		        			 {
		        				 formParams.add(new BasicNameValuePair(key+"["+i+"]",valuearray[i])); 
		        			 }
	        			 }
	        		 }
	        		 else
	        		 {
	        			 formParams.add(new BasicNameValuePair(key, (String) value));    
	        		 }
		        }
	        }
	        HttpEntity httpentity = new UrlEncodedFormEntity(formParams, enc);   
	        method = new HttpPost(path);
	        if(headers!=null && headers.size()>0)
	        {
	        	Header[] hds=new Header[]{};
	 	        int i=0;
	 	        for(String key:headers.keySet())
	 	        {
	 	        	hds[i++]=new BasicHeader(key,headers.get(key));
	 	        }
	 			method.setHeaders(hds);
	        }
	        
	        HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyhost!=null && proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	method.setEntity(httpentity);
	        
			WebClientDevWrapper.wrapClient(httpClient);
			response = httpClient.execute( method, context);
			try{
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String str = EntityUtils.toString(response.getEntity());
					map.put("data", str);
					map.put("returncode", "00000000");
					return map;
				} else {
					map.put("returncode", "00000001");
					map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString() );
					return map;
				}
			}catch(Exception ex)
			{
				map.put("returncode", "00000001");
				map.put("returnmsg", ex.getMessage());
			}finally 
			{
				if(null!= method)    
				{
					method.releaseConnection();    
				}
				response.close();
			}
			
			
		} catch (ClientProtocolException e) {
			map.put("returnmsg", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			map.put("returnmsg", e.getMessage());
			e.printStackTrace();
		} finally 
		{
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return map;   
    }
    
    
//    public static HashMap sendPostZHitong(String path,Map<String,String>  params,String enc,String proxyhost,int proxyport)   
//    {
//    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
//    	for(String key:params.keySet())
//    	{
//    		Object value=params.get(key);
//    		String[] valuearray=new String[]{value.toString()};
//			paramresult.put(key, valuearray);
//    	}
//    	return sendPostZHitong(path,null,paramresult,enc,proxyhost,proxyport);
//    }
//    
//    public static HashMap sendPostZHitong(String path, Map<String, String> headers, Map<String, String[]> params, String enc,String proxyhost,int proxyport)   
//    {
//    	HashMap map=new HashMap();
//    	map.put("returncode", "00000001");
//        map.put("returnmsg", "");
//        map.put("data", "");
//        HttpPost method =null;
//        CloseableHttpResponse response=null;
//        try 
//		{
//	        List<NameValuePair> formParams = new ArrayList<NameValuePair>();   
//	        if(params!=null)
//	        {
//	        	for(String key: params.keySet())   
//		        {
//	        		 Object value=params.get(key);
//	        		 if(value instanceof   String[] )
//	        		 {
//	        			 String valuearray[]=(String[])value;
//	        			 if(valuearray.length==1)
//	        			 {
//	        				 formParams.add(new BasicNameValuePair(key,valuearray[0])); 
//	        			 }else{
//	        				 for(int i=0;i<valuearray.length;i++)
//		        			 {
//		        				 formParams.add(new BasicNameValuePair(key+"["+i+"]",valuearray[i])); 
//		        			 }
//	        			 }
//	        		 }
//	        		 else
//	        		 {
//	        			 formParams.add(new BasicNameValuePair(key, (String) value));    
//	        		 }
//		        }
//	        }
//	        HttpEntity httpentity = new UrlEncodedFormEntity(formParams, enc);   
//	        method = new HttpPost(path);
//	        if(headers!=null && headers.size()>0)
//	        {
//	        	Header[] hds=new Header[]{};
//	 	        int i=0;
//	 	        for(String key:headers.keySet())
//	 	        {
//	 	        	hds[i++]=new BasicHeader(key,headers.get(key));
//	 	        }
//	 			method.setHeaders(hds);
//	        }
//	        
//	        HttpClientContext  context = HttpClientContext.create();
//			Builder builder = RequestConfig.custom();
//			if(proxyhost!=null && proxyport>0)
//			{
//				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
//				builder.setProxy(proxy);
//			}
//			
//			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
//	    	method.setConfig(requestConfig);
//	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
//	    	method.setConfig(requestConfig);
//	    	method.setEntity(httpentity);
//	        
//			WebClientDevWrapper.wrapClient(httpClient);
//			response = httpClient.execute( method, context);
//			try{
//				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//					String str = EntityUtils.toString(response.getEntity());
//					map.put("data", str);
//					map.put("returncode", "00000000");
//					return map;
//				} else {
//					map.put("returncode", "00000001");
//					map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString() );
//					return map;
//				}
//			}catch(Exception ex)
//			{
//				map.put("returncode", "00000001");
//				map.put("returnmsg", ex.getMessage());
//			}finally 
//			{
//				if(null!= method)    
//				{
//					method.releaseConnection();    
//				}
//				response.close();
//			}
//			
//			
//		} catch (ClientProtocolException e) {
//			map.put("returnmsg", e.getMessage());
//			e.printStackTrace();
//		} catch (IOException e) {
//			map.put("returnmsg", e.getMessage());
//			e.printStackTrace();
//		} finally 
//		{
//			if(method !=null )    
//			{
//				method.releaseConnection();    
//			}
//			if(response!=null)
//			{
//				try {
//					response.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//        return map;   
//    }
    
    
    public static HashMap sendPost(String path, Map<String, String> params)   
    {
    	return sendPost(path,params,"UTF-8");
    }
    
    
    public static HashMap sendPostForMultipart(String path, Map<String, Object> params, String enc)    
    {
    	HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
        HttpPost method =null;
        CloseableHttpResponse response=null;
    	try 
		{
	        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	        for(Map.Entry<String, Object> entry : params.entrySet())   
	        {
	        		 if (entry.getValue() instanceof File)
	        		 {
	        			 builder.addPart("param3", new FileBody((File) entry.getValue()));  
	        		 }
	        		 else{
	        			 builder.addPart(entry.getKey(), new StringBody((String) entry.getValue(), Charset.forName(enc)));  
	        		 }
	        }
	        HttpEntity mentity =builder.build();
	        method = new HttpPost(path);
	        HttpContext context = HttpClientContext.create();
	    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	method.setEntity(mentity); 
			WebClientDevWrapper.wrapClient(httpClient);
			response = httpClient.execute(method,context);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(response.getEntity());
//				//logger.debug("recivehttp:"+str);
				map.put("data", str);
				map.put("returncode", "00000000");
				return map;
			} else {
				map.put("returncode", "00000001");
				return map;
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			map.put("returnmsg", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			map.put("returnmsg", e.getMessage());
		} finally 
		{
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return map; 
    }
    
    public static HashMap sendPostForMultipart(String path, Map<String, Object> params) throws Exception   
    {
    	return sendPostForMultipart(path, params,"UTF-8");
    }
    
    
	
	public static HashMap sendJson(String url,String json){
		return sendJson(url,json,null,0);
	}
	
	public static HashMap sendJson(String url,String json,String proxyhost,int proxyport){
		HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
		HttpPost method = new HttpPost(url);
		//post.setHeader("Accept", "application/json");
		method.setHeader("Content-Type","application/json");
		method.setHeader("charset",HTTP.UTF_8);
		//post.setHeader("Content-Type","application/json");
		CloseableHttpResponse  response =null;
		try {
			////logger.debug("json:"+json);
			StringEntity s = new StringEntity(json.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			method.setEntity(s);
			
			
			HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
	    	RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
			
//	    	
//	    	org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
//	    	context.setCookieStore(cookieStore);
	    	
	    	
	    	HttpResponse res = httpClient.execute(method,context);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String str = IOUtils.toString(new InputStreamReader(res.getEntity().getContent(), "UTF-8"));
				////logger.debug("recivehttp:"+str);
				map.put("data", str);
				map.put("returncode", "00000000");
			}
			else 
			{
				map.put("returncode", "00000001");
				map.put("returnmsg",res.getStatusLine().getStatusCode());
			}
		}  catch (Exception e) {
			e.printStackTrace();
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public static HashMap sendJson(String url,Map<String, String> params,String json) throws UnsupportedEncodingException{
		HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
		
		
		StringBuilder sb = new StringBuilder(url);
		sb.append('?');
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=')
			.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
			.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);
		
		HttpPost method = new HttpPost(sb.toString());
		CloseableHttpResponse  response =null;
		method.setHeader("Content-Type","application/json");
		method.setHeader("charset",HTTP.UTF_8);
		try {
			StringEntity s = new StringEntity(json.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			method.setEntity(s);
			
			HttpContext context = HttpClientContext.create();
	    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
			response= httpClient.execute(method,context);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String str = IOUtils.toString(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				map.put("data", str);
				map.put("returncode", "00000000");
			}
			else 
			{
				map.put("returncode", "00000001");
			}
		}  catch (IOException e) {
			e.printStackTrace();
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
//	
//	gzip
//	public static void sendHttpContent(String jsonStr) throws Exception {
//		// ///////////////压缩文本内容////////////////////
//		byte[] reqbytes = GzipUtils.encompressingGZipString(jsonStr);
//		BigDecimal bdec = new BigDecimal(jsonStr.getBytes().length).divide(new BigDecimal(1024)).setScale(2, RoundingMode.HALF_UP);
//		BigDecimal adec = new BigDecimal(reqbytes.length).divide(new BigDecimal(1024)).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("压缩前的数据大小为：" + bdec + "KB,压缩后的数据大小为：" + adec + "KB");
//		// ///////////////压缩////////////////////
//		HttpClient client = new DefaultHttpClient();
//		// /////////////////////设置双向证书验证------自己在写测试时,可去先去掉///////////////
//		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		FileInputStream instream = new FileInputStream(new File("D:/ssl/client/client.p12"));
//		try {
//			keyStore.load(instream, "hymax1601sp".toCharArray());
//		} finally {
//			instream.close();
//		}
//
//		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//		FileInputStream instream2 = new FileInputStream(new File("D:/ssl/client/client.truststore"));
//		try {
//			trustStore.load(instream2, "max1601hysp".toCharArray());
//		} finally {
//			instream.close();
//		}
//		SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, "hymax1601sp", trustStore);
//		socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		Scheme sch = new Scheme("https", 8443, socketFactory);
//		client.getConnectionManager().getSchemeRegistry().register(sch);
//		// ////////////////////设置双向证书验证------自己在写测试时,可去先去掉///////////////
//		// ///发送http 请求/////
//		HttpPost post = new HttpPost(JKURL);
//		post.setHeader("Content-Encoding", "gzip"); // 设置接受响应消息为gzip
//		ByteArrayEntity bae = new ByteArrayEntity(reqbytes);
//		post.setEntity(bae);
//		HttpResponse response = client.execute(post);
//		String resStr = GzipUtils.getGZipString(response);
//		System.out.println("回调内容:" + resStr);
//	}
//
//	
	
	public static HashMap sendXMl(String url,String xml){
		//return sendXMl(url,xml,"119.254.84.180",8666);
		return sendXMl(url,xml,null,0);
	}
	
	public static HashMap sendXMl(String url,String xml,String proxyhost,int proxyport){
		
		HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
		HttpPost method = new HttpPost(url);
		//post.setHeader("Accept", "application/json");
		method.setHeader("Content-Type","text/xml");
		method.setHeader("charset",HTTP.UTF_8);
		//post.setHeader("Content-Type","application/json");
		CloseableHttpResponse  response =null;
		try {
			////logger.debug("json:"+json);
			StringEntity s = new StringEntity(xml.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("text/xml");
			method.setEntity(s);
			
			
			HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyport>0)
			{
				//url.startsWith("https")?"https":
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			
			//HttpClientContext  context = HttpClientContext.create();
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	
			method.setConfig(requestConfig);
	    	
	    	response = httpClient.execute(method,context);
	    	
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String str = IOUtils.toString(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				map.put("data", str);
				map.put("returncode", "00000000");
			}
			else 
			{
				map.put("returncode", response.getStatusLine().getStatusCode());
			}
		}  catch (Exception e) {
			e.printStackTrace();
			map.put("returncode", "00000001");
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	
	 public static String getFileMd5(String path) throws ClientProtocolException, IOException   
	 {
		 	HttpGet request = new HttpGet(path);
			WebClientDevWrapper.wrapClient(httpClient);
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return DigestUtils.md5Hex(response.getEntity().getContent());
			}
			return null;
	 }
	 
	
	
	
//	
//	public static void copy(InputStream fis, OutputStream fos,int bufferSize)
//			   throws IOException
//	{
////		   byte[] buf = new byte[bufferSize];
////		   int len = 0;
////		   while ((len = in.read(buf)) >= 0)
////		   {
////		      out.write(buf, 0, len);
////		   }
//		
//		int readLen = 0;
//		byte[] buf = new byte[1024];
//		while ((readLen = fis.read(buf)) != -1) {
//			//logger.debug(readLen);
//			fos.write(buf, 0, readLen);
//		}
//		fos.flush();
//		fos.close();
//		fis.close();
//		
//	}

	
	
	/*
	 * 进行md5加密生成指纹信息
	 */
	public static String getFileDigest(String path) throws Exception  
	{
		URL url = new URL(path);
		InputStream is = url.openStream();
		MessageDigest md = MessageDigest.getInstance("MD5");
		String digest = getDigest(is, md, 2048);
		return digest;
	}
	
	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize) throws NoSuchAlgorithmException, IOException {
		md.reset();
		byte[] bytes = new byte[byteArraySize];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		String result = new String(Hex.encodeHex(digest));
		return result;
	}
	
	
	public static HashMap downloadFile(String url, OutputStream target) throws ClientProtocolException, IOException{
		HashMap map=new HashMap();
		map.put("returncode", "11111111");
        map.put("returnmsg", "");
		HttpGet request = new HttpGet(url);
		WebClientDevWrapper.wrapClient(httpClient);
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String dc  = Charset.defaultCharset().name();  
				HttpEntity entity = response.getEntity();
				if (entity != null) {
				        InputStream inputStream = entity.getContent();
				        IOUtils.copy(inputStream, target);
				}
				map.put("returncode", "00000000");
				return map;
			} else {
				map.put("returncode", "00000001");
				return map;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static  void downloadAndSaveToFile(String url, File targetFile) throws ClientProtocolException, IOException {
		if(targetFile.exists())
		{
			targetFile.delete();
		}
	    OutputStream outputStream = new FileOutputStream(targetFile);
	    downloadFile(url, outputStream);
	    outputStream.close();
	}
	
	public static  void downloadAndSaveToFile(String url, String targetFile) throws ClientProtocolException, IOException {
		downloadAndSaveToFile(url,new File(targetFile));
	}
	
	
    public static void printHTTP(HttpServletRequest request) throws UnsupportedEncodingException{
    	printHTTP(request,"UTF-8");
    }
	
    
	public static void printHTTP(HttpServletRequest request,String encode) throws UnsupportedEncodingException{
		//String reqpath=saveHttp(request);
		
		if(logger.isDebugEnabled())
		{
			logger.debug("==========================================================================");
			logger.debug("HTTP-request info start");
			logger.debug("getCharacterEncoding:"+request.getCharacterEncoding());
			logger.debug("getContentType:"+request.getContentType());
			logger.debug("getRequestedSessionId:"+request.getRequestedSessionId());
		    logger.debug("getMethod:"+request.getMethod());
		    logger.debug("getPathInfo:"+request.getPathInfo());
		    logger.debug("getQueryString:"+request.getQueryString());
		    logger.debug("getRequestURI:"+request.getRequestURI());
		    logger.debug("getServerPort:"+request.getServerPort());
		    logger.debug("getRequestURL:"+request.getRequestURL().toString());
		    logger.debug("getScheme:"+request.getScheme());
		    logger.debug("getServerName:"+request.getServerName());
		    logger.debug("getContextPath:"+request.getContextPath());
		    logger.debug("getServletPath:"+request.getServletPath());
		    logger.debug("getRemoteAddr:"+request.getRemoteAddr());
		    logger.debug("getRemoteHost:"+request.getRemoteHost());
		    logger.debug("getRemotePort:"+request.getRemotePort());
		    logger.debug("getRemoteUser:"+request.getRemoteUser());
		    logger.debug("getRequestedSessionId:"+request.getRequestedSessionId());
		    logger.debug("HTTP-request info end");
		    
		    
		    
		    logger.debug("HTTP-Header:start");
			request.setCharacterEncoding(encode);
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) 
			{
				String headerName = (String) headerNames.nextElement();
				String headerValues = request.getHeader(headerName);
				logger.debug("HeaderName:"+headerName+"   HeaderValue:"+headerValues);
			}
			logger.debug("HTTP-Header:end");
			
			logger.debug("HTTP-Body:start");
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) 
			{
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				for(int i=0;i<paramValues.length;i++)
				{
					////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
					logger.debug("ParamName:"+paramName+"   ParamValue:"+paramValues[i]);
				}
			}
			logger.debug("HTTP-Body:end");
			
			
			logger.debug("HTTP-Session:start");
			
			if(request.getSession(false)!=null)
			{
				Enumeration en = request.getSession(false).getAttributeNames();
				while(en.hasMoreElements())
				{
					String str=(String) en.nextElement();
					logger.debug("ParamName:"+str+"   ParamValue:"+request.getSession().getAttribute(str));
				}
			}
			logger.debug("HTTP-Session:end");
			
			
			
			logger.debug("HTTP-Cookie:start");
			if(request.getCookies()!=null)
			{
				Cookie[] cookie = request.getCookies();
				for(Cookie c:cookie)
				{
					logger.debug("   Name:"+c.getName()+"  Secure:"+c.getSecure()+"   Value:"+c.getValue()+"  Comment:"+c.getComment()+"   MaxAge:"+c.getMaxAge()+"   Path:"+c.getPath()+"   Version:"+c.getVersion()+"   Domain:"+c.getDomain());
				}
			}
			
			logger.debug("HTTP-Cookie:end");
			logger.debug("==========================================================================");
		}
	}
	
	
	public static void printHTTP(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
//		//logger.debug("requestcharset:"+request.getCharacterEncoding());
//		//logger.debug("HTTP-Header:start");
		request.setCharacterEncoding("utf-8");
		
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) 
		{
			String headerName = (String) headerNames.nextElement();
			String headerValues = request.getHeader(headerName);
//			//logger.debug("HeaderName:"+headerValues+"   HeaderValue:"+headerValues);
		}
		//logger.debug("HTTP-Header:end");
		
		//logger.debug("HTTP-Body:start");
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				//logger.debug("ParamName:"+paramName+"   ParamValue:"+paramValues[i]);
			}
		}
		//logger.debug("HTTP-Body:end");
		
		
		//logger.debug("HTTP-Session:start");
		if(request.getSession(false)!=null)
		{
			Enumeration en = request.getSession(false).getAttributeNames();
			while(en.hasMoreElements())
			{
				String str=(String) en.nextElement();
				//logger.debug("ParamName:"+str+"   ParamValue:"+request.getSession().getAttribute(str));
			}
		}
		//logger.debug("HTTP-Session:end");
		
		//logger.debug("HTTP-Cookie:start");
		if(request.getCookies()!=null)
		{
			Cookie[] cookie = request.getCookies();
			for(Cookie c:cookie)
			{
				//logger.debug("   Name:"+c.getName()+"  Secure:"+c.getSecure()+"   Value:"+c.getValue()+"Comment:"+c.getComment()+"   MaxAge:"+c.getMaxAge()+"   Path:"+c.getPath()+"   Version:"+c.getVersion()+"   Domain:"+c.getDomain());
			}
			
		}
		//logger.debug("HTTP-Cookie:end");
	}
	
	

	public static  String getHTTP(HttpServletRequest request) throws UnsupportedEncodingException{
		return getHTTP(request,"UTF-8");
	}
	
	public static  String getHTTP(HttpServletRequest request,String ecode) throws UnsupportedEncodingException{
		StringBuffer sb=new StringBuffer("");
		sb.append("------------------------------------------------------------------\n");
		sb.append("method:"+request.getMethod()+"   charset:"+request.getCharacterEncoding());
		sb.append("HTTP-Header:start\n");
		request.setCharacterEncoding(ecode);
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) 
		{
			String headerName = (String) headerNames.nextElement();
			String headerValues = request.getHeader(headerName);
			sb.append("HeaderName:"+headerValues+"   HeaderValue:"+headerValues+"\n");
		}
		sb.append("HTTP-Header:end\n");
		
		sb.append("HTTP-Body:start\n");
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				sb.append("ParamName:"+paramName+"   ParamValue:"+paramValues[i]+"\n");
			}
		}
		sb.append("HTTP-Body:end"+"\n");
		sb.append("------------------------------------------------------------------\n");
		
		sb.append("HTTP-Session:start\n");
		Enumeration en = request.getSession().getAttributeNames();
		while(en.hasMoreElements())
		{
			String str=(String) en.nextElement();
			sb.append("ParamName:"+str+"   ParamValue:"+request.getSession().getAttribute(str)+"\n");
		}
		sb.append("HTTP-Session:end\n");
		return sb.toString();
	}
	
	
	public static  String getHTTPJSONString(HttpServletRequest request) throws Exception
	{
		return getHTTPJSONString(request,"UTF-8");
	}
	
	
	public static  String getHTTPJSONString(HttpServletRequest request,String ecode) throws Exception
	{
		ObjectMapper om=JsonHelper.getObjectMapperInstance();
		HashMap params=new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				String value=null;
				if(params.containsKey(paramName))
				{
					String aa=new String(paramValues[i].getBytes("ISO8859-1"),ecode);
					value=params.get(paramName)+aa;
				}else{
					value=new String(paramValues[i].getBytes("ISO8859-1"),ecode);
				}
				params.put(paramName,value);
			}
		}
		String param=om.writeValueAsString(params);
		return param;
	}
	
	public static void printHTTP(HttpServletRequest request,Logger log) throws UnsupportedEncodingException{
		log.error("HTTP-Header:start");
		request.setCharacterEncoding("gbk");
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) 
		{
			String headerName = (String) headerNames.nextElement();
			String headerValues = request.getHeader(headerName);
			log.error("HeaderName:"+headerValues+"   HeaderValue:"+headerValues);
		}
		log.error("HTTP-Header:end");
		
		log.error("HTTP-Body:start");
		Enumeration paramNames = request.getParameterNames();
		
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				log.error("ParamName:"+paramName+"   ParamValue:"+paramValues[i]);
			}
		}
		log.error("HTTP-Body:end");
	}
	
	public static  String genToken(HttpServletRequest request) {
//		 try {
//			printHTTP(request);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		 String token=Guid.genRandom(20);
	   	 request.getSession().getServletContext().setAttribute("token",token);
	   	 return token;
	}
	
	public static boolean isInValidToken(HttpServletRequest request,
			String tokenreq) throws Exception {
		if (1 == 1) {
			return false;
		}
		// printHTTP(request);
		String token = (String) request.getSession().getServletContext()
				.getAttribute("token");
		// .getServletContext()
		if (token == null) {
			throw new Exception("token not exist on server");
		}
		if (token.equals(tokenreq)) {
			return true;
		}
		return false;
	}
	
	
	static String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
			"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
			"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
			"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
			"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
			"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
			"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
			"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
			"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
			"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
			"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
			"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
			"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
			"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
			"Googlebot-Mobile" };
	//http://www.aiisen.com/java-judge-is-moblie-zh.html
	public static boolean judgeIsMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}
	
	public static boolean judgeIsWeiXin(HttpServletRequest request) {
		boolean isweixin = false;
		if (request.getHeader("User-Agent") != null) {
			if (request.getHeader("User-Agent").toLowerCase().indexOf("micromessenger") >= 0) {
				isweixin = true;
			}
		}
		return isweixin;
	}
	
	public static String saveHttp(HttpServletRequest request)
	{
		try {
			String batch=Guid.getUniqueIdWithNaos();
			String reqpath="/logs/"+batch+".req.txt";
			SaveHttpRequest saverequest=new SaveHttpRequest(request);
			ObjectMapper om =JsonHelper.getObjectMapperInstance();
			if("POST".equals(request.getMethod()) && request.getContentLength()>0)
			{
				String datapath="/logs/"+batch+".data.txt";
				om.writeValue(new File(reqpath), saverequest);
				OutputStream output=new FileOutputStream(datapath);
				IOUtils.copy(request.getInputStream(), output);
				saverequest.setDatafilepath(datapath);
			}
			om.writeValue(new File(reqpath),saverequest);
			return reqpath;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static HashMap getHashMap(HttpServletRequest request) throws UnsupportedEncodingException 
	{
		Map<String, String[]> parameters = request.getParameterMap();
	    HashMap<String, String> reslutmap = new HashMap();
	    for(String paramName : parameters.keySet()) {
	    		String[] paramValues = parameters.get(paramName);
	        	StringBuffer valueStr=new StringBuffer();
	        	String[]  strarray=(String[]) paramValues;
	        	for (int i = 0; i < strarray.length; i++) {
	        		valueStr.append(strarray[i]);
	        		if(i!=strarray.length-1)
	        		{
	        			valueStr.append(",");
	        		}
				}
				reslutmap.put(paramName,new String(valueStr));
	    }
	    return reslutmap;
	}
	
	
	public static HashMap getHashMap(HttpServletRequest request,String enc) throws UnsupportedEncodingException 
	{
		return  getHashMap(request,"ISO8859-1",enc);
	}
	
	public static HashMap getHashMap(HttpServletRequest request,String sourceenc,String enc) throws UnsupportedEncodingException 
	{
		Map<String, String[]> parameters = request.getParameterMap();
	    HashMap<String, String> reslutmap = new HashMap();
	    for(String paramName : parameters.keySet()) {
	    		String[] paramValues = parameters.get(paramName);
	        	StringBuffer valueStr=new StringBuffer();
	        	String[]  strarray=(String[]) paramValues;
	        	for (int i = 0; i < strarray.length; i++) {
	        		valueStr.append(strarray[i]);
	        		if(i!=strarray.length-1)
	        		{
	        			valueStr.append(",");
	        		}
				}
				reslutmap.put(paramName,new String(valueStr.toString().getBytes(sourceenc),enc));
	    }
	    return reslutmap;
	}
	
	
	public static  HashMap sendBySavedFile(String reqfilepath) throws IOException
	{
		ObjectMapper om =JsonHelper.getObjectMapperInstance();
		SaveHttpRequest save=om.readValue(new File(reqfilepath), SaveHttpRequest.class);
		if("GET".equals(save.getMethod()))
		{
			return sendGet(save.getRedirectUrl(), save.getHeaders2(),null,"UTF-8");
		}else if("POST".equals(save.getMethod()))
		{
			if(save.getContentLength()==0)
			{
				InputStream is=new FileInputStream(save.getDatafilepath());
				return sendPost(save.getRedirectUrl(),save.getHeaders2(),is);
			}
			else
			{
				return sendPost(save.getRedirectUrl(),save.getHeaders2(),save.getParameterMap());
			}
		}
		return null;
	}
	
	
}  

//org.springframework.mobile.device.LiteDeviceResolver
//private static final String[] KNOWN_MOBILE_USER_AGENT_PREFIXES = new String[] {
//	"w3c ", "w3c-", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
//	"bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco",
//	"eric", "hipt", "htc_", "inno", "ipaq", "ipod", "jigs", "kddi", "keji",
//	"leno", "lg-c", "lg-d", "lg-g", "lge-", "lg/u", "maui", "maxo", "midp",
//	"mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-", "newt", "noki",
//	"palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage",
//	"sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-",
//	"siem", "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-",
//	"tosh", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi",
//	"wapp", "wapr", "webc", "winw", "winw", "xda ", "xda-" };
//
//private static final String[] KNOWN_MOBILE_USER_AGENT_KEYWORDS = new String[] {
//	"blackberry", "webos", "ipod", "lge vx", "midp", "maemo", "mmp", "mobile",
//	"netfront", "hiptop", "nintendo DS", "novarra", "openweb", "opera mobi",
//	"opera mini", "palm", "psp", "phone", "smartphone", "symbian", "up.browser",
//	"up.link", "wap", "windows ce" };
//
//private static final String[] KNOWN_TABLET_USER_AGENT_KEYWORDS = new String[] {
//	"ipad", "playbook", "hp-tablet", "kindle" };
//






//User Agent Detection Libraries
//
//This is a list of User Agent detection libraries to be tested for compatibility with Firefox OS and Firefox for Android. This may also include general purpose JS libraries that include some kind of UA detection. 
//
//The end goal is to contact these sites or (even better, in the case of open source libraries) provide patches so our mobile browsers are correctly identified as mobile (and not served desktop content or blocked outright). 
//
//NOTE: This page won't be actively updated anymore. You can watch the meta bug, or track progress at https://github.com/miketaylr/arewedetectableyet/. 
//
//
//UA strings to test with: 
//
//Firefox Mobile for Android: 
//Mozilla/5.0 (Android; Mobile; rv:24.0) Gecko/24.0 Firefox/24.0 
//
//Firefox OS phones: 
//Mozilla/5.0 (Mobile; rv:18.0) Gecko/18.0 Firefox/18.0 
//
//Firefox OS tablets: 
//Mozilla/5.0 (Tablet; rv:29.0) Gecko/29.0 Firefox/29.0 
//
//Some known Firefox OS UA strings with device ids (found in bugzil.la/873385): 
//Mozilla/5.0 (Mobile; ZTEOPEN; rv:18.1) Gecko/18.1 Firefox/18.1 
//Mozilla/5.0 (Mobile; HUAWEIY300-F1; rv:18.1) Gecko/18.1 Firefox/18.1 
//Mozilla/5.0 (Mobile; LG-D300; rv:18.1) Gecko/18.1 Firefox/18.1 
//Mozilla/5.0 (Mobile; ALCATELOneTouch4012X; rv:18.1) Gecko/18.1 Firefox/18.1 
//
//See also https://developer.mozilla.org/en-US/docs/Gecko_user_agent_string_reference#Firefox_OS_version_number. 
//
//Libraries to be tested
//
//JavaScript
// Dojo Toolkit https://github.com/dojo/ (see http://dojotoolkit.org/reference-guide/1.7/quickstart/browser-sniffing.html)  Do we need to recommend a ffos or fxos token be added to the module? https://github.com/dojo/dojo/blob/master/sniff.js 
//
//
//Server Modules
// NetBiscuits http://www.netbiscuits.com/ (in contact via email.) 
//
//Tested, issues reported in Bugzilla
//
//Meta Bug: 915706 
//
//Libraries known to be compatibile
//
//Go
// UserAgent https://github.com/mssola/user_agent 
//
//Java
// UADetector https://github.com/before/uadetector 
// User Agent Utils 915727 * 
//
//JavaScript
// Detect.js https://github.com/darcyclarke/Detect.js 
// Detectizr Bug 956998 * 
// Device.js 915853 * 
// Express Device https://npmjs.org/package/express-device 
// isMobile https://github.com/kaimallea/isMobile 
// is-mobile Bug 914231 * 
// JS Redirection Mobile Bug 932458* 
// jQuery Mobile 
// Mobile Agent https://github.com/fabdrol/mobileagent 
// Mootools (https://github.com/mootools/mootools-core/blob/master/Source/Browser/Browser.js) 
// Node User Agent Parse https://github.com/jujhars13/node-user-agent-parse 
// Platform.js Bug 913633 
// ua-parser-js https://github.com/faisalman/ua-parser-js 
// useragent https://github.com/3rd-Eden/useragent 
// Woothee Bug 914313 * 
// YUI - http://yuilibrary.com/yui/docs/yui/yui-ua.html 
// UA.js Bug 913189 * 
//
//Multiple Languages
// ua-parser (multiple languages) https://github.com/tobie/ua-parser 
// Mobile ESP http://blog.mobileesp.com/  Some ports of this are compatible, in contact with maintainer via email. 
//
// detectmobilebrowsers.com Bug 914241* 
//
//Perl
// HTTP::BrowserDetect https://github.com/oalders/http-browserdetect 
//
//PHP
// Categorizr https://github.com/bjankord/Categorizr (Note: no longer maintained) 
// Detector (Drupal) https://drupal.org/project/detector / http://detector.dmolsen.com/ 
// Mobile-Detect https://github.com/serbanghita/Mobile-Detect / http://mobiledetect.net/ / http://code.google.com/p/php-mobile-detect/ 
// Mobile Detect Bundle https://github.com/suncat2000/MobileDetectBundle 
// PHP User Agent https://github.com/ornicar/php-user-agent 
// PHP User Agent Parser https://github.com/donatj/PhpUserAgent 
// WordPress Mobile Detect http://wordpress.org/plugins/wp-mobile-detect/ 
// WPTouch (WordPress) Bug 909420 
//
//Python
// Django Mobile https://github.com/gregmuellegger/django-mobile 
// GoMobile (Plone) https://pypi.python.org/pypi/gomobile.mobile 
// minidetector https://github.com/saschwarz/minidetector/network 
// Python User Agents https://github.com/selwin/python-user-agents 
//
//Ruby
// agent_orange https://github.com/kevinelliott/agent_orange 
// browser https://github.com/fnando/browser 
// Mobvious https://github.com/jistr/mobvious (uses MobileESP (ruby port is at least compatible) 
// UserAgent https://github.com/josh/useragent (makes no mobile/non-mobile distinction) 
// User Agent Parser https://github.com/toolmantim/user_agent_parser 
//
//Server Detection Frameworks
// Apache Mobile Filter 916250 
// 51 Degrees http://51degrees.mobi/Products/DeviceDetection.aspx 
// Detect Right http://detectright.com/ 
// DeviceAtlas https://deviceatlas.com/device-data/user-agent-tester 
//http://handsetdetection.com Bug 957205 
// OpenDDR http://openddr.org/ 
// WURFL (1.4) http://tools.scientiamobile.com/?user-agent-string=Mozilla%2F5.0+%28Mobile%3B+rv%3A18.0%29+Gecko%2F18.0+Firefox%2F18.0 
//
//	 

/*
 * package com.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void jUnitTest() {
		get();
	}
	public void ssl() {
		CloseableHttpClient httpclient = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
			try {
				// 加载keyStore d:\\tomcat.keystore  
				trustStore.load(instream, "123456".toCharArray());
			} catch (CertificateException e) {
				e.printStackTrace();
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}
			// 相信自己的CA和所有自签名的证书
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
			// 只允许使用TLSv1协议
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(get方式)
			HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
			//logger.debug("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				//logger.debug("----------------------------------------");
				//logger.debug(response.getStatusLine());
				if (entity != null) {
					//logger.debug("Response content length: " + entity.getContentLength());
					//logger.debug(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void postForm() {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
		// 创建参数队列  
		List<namevaluepair> formparams = new ArrayList<namevaluepair>();
		formparams.add(new BasicNameValuePair("username", "admin"));
		formparams.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			//logger.debug("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					//logger.debug("--------------------------------------");
					//logger.debug("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					//logger.debug("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void post() {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
		// 创建参数队列  
		List<namevaluepair> formparams = new ArrayList<namevaluepair>();
		formparams.add(new BasicNameValuePair("type", "house"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			//logger.debug("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					//logger.debug("--------------------------------------");
					//logger.debug("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					//logger.debug("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void get() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.  
			HttpGet httpget = new HttpGet("http://www.baidu.com/");
			//logger.debug("executing request " + httpget.getURI());
			// 执行get请求.  
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体  
				HttpEntity entity = response.getEntity();
				//logger.debug("--------------------------------------");
				// 打印响应状态  
				//logger.debug(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度  
					//logger.debug("Response content length: " + entity.getContentLength());
					// 打印响应内容  
					//logger.debug("Response content: " + EntityUtils.toString(entity));
				}
				//logger.debug("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void upload() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");

			FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

			httppost.setEntity(reqEntity);

			//logger.debug("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				//logger.debug("----------------------------------------");
				//logger.debug(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					//logger.debug("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}



import java.util.concurrent.CountDownLatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
public class AsyncClientHttpExchangeFutureCallback {
    public static void main(final String[] args) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000).setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[] {
                    new HttpGet("http://www.apache.org/"),
                    new HttpGet("https://www.verisign.com/"),
                    new HttpGet("http://www.google.com/"),
                    new HttpGet("http://www.baidu.com/") };
            final CountDownLatch latch = new CountDownLatch(requests.length);
            for (final HttpGet request : requests) {
                httpclient.execute(request, new FutureCallback<httpresponse>() {
                    //无论完成还是失败都调用countDown()
                    @Override
                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->"
                                + response.getStatusLine());
                    }
                    @Override
                    public void failed(final Exception ex) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->" + ex);
                    }
                    @Override
                    public void cancelled() {
                        latch.countDown();
                        System.out.println(request.getRequestLine()
                                + " cancelled");
                    }
                });
            }
            latch.await();
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }
}
















Chapter 3. HTTP state management
Prev 	 	 Next
Chapter 3. HTTP state management
Originally HTTP was designed as a stateless, request / response oriented protocol that made no special provisions for stateful sessions spanning across several logically related request / response exchanges. As HTTP protocol grew in popularity and adoption more and more systems began to use it for applications it was never intended for, for instance as a transport for e-commerce applications. Thus, the support for state management became a necessity.
Netscape Communications, at that time a leading developer of web client and server software, implemented support for HTTP state management in their products based on a proprietary specification. Later, Netscape tried to standardise the mechanism by publishing a specification draft. Those efforts contributed to the formal specification defined through the RFC standard track. However, state management in a significant number of applications is still largely based on the Netscape draft and is incompatible with the official specification. All major developers of web browsers felt compelled to retain compatibility with those applications greatly contributing to the fragmentation of standards compliance.
3.1. HTTP cookies
An HTTP cookie is a token or short packet of state information that the HTTP agent and the target server can exchange to maintain a session. Netscape engineers used to refer to it as a "magic cookie" and the name stuck.
HttpClient uses the Cookie interface to represent an abstract cookie token. In its simplest form an HTTP cookie is merely a name / value pair. Usually an HTTP cookie also contains a number of attributes such as version, a domain for which is valid, a path that specifies the subset of URLs on the origin server to which this cookie applies, and the maximum period of time for which the cookie is valid.
The SetCookie interface represents a Set-Cookie response header sent by the origin server to the HTTP agent in order to maintain a conversational state. The SetCookie2 interface extends SetCookie with Set-Cookie2 specific methods.
The ClientCookie interface extends Cookie interface with additional client specific functionality such as the ability to retrieve original cookie attributes exactly as they were specified by the origin server. This is important for generating the Cookie header because some cookie specifications require that the Cookie header should include certain attributes only if they were specified in the Set-Cookie or Set-Cookie2 header.
3.1.1. Cookie versions
Cookies compatible with Netscape draft specification but non-compliant with the official specification are considered to be of version 0. Standard compliant cookies are expected to have version 1. HttpClient may handle cookies differently depending on the version.
Here is an example of re-creating a Netscape cookie:
BasicClientCookie netscapeCookie = new BasicClientCookie("name", "value");
netscapeCookie.setVersion(0);
netscapeCookie.setDomain(".mycompany.com");
netscapeCookie.setPath("/");
Here is an example of re-creating a standard cookie. Please note that standard compliant cookie must retain all attributes as sent by the origin server:
BasicClientCookie stdCookie = new BasicClientCookie("name", "value");
stdCookie.setVersion(1);
stdCookie.setDomain(".mycompany.com");
stdCookie.setPath("/");
stdCookie.setSecure(true);
// Set attributes EXACTLY as sent by the server 
stdCookie.setAttribute(ClientCookie.VERSION_ATTR, "1");
stdCookie.setAttribute(ClientCookie.DOMAIN_ATTR, ".mycompany.com");
Here is an example of re-creating a Set-Cookie2 compliant cookie. Please note that standard compliant cookie must retain all attributes as sent by the origin server:
BasicClientCookie2 stdCookie = new BasicClientCookie2("name", "value");
stdCookie.setVersion(1);
stdCookie.setDomain(".mycompany.com");
stdCookie.setPorts(new int[] {80,8080});
stdCookie.setPath("/");
stdCookie.setSecure(true);
// Set attributes EXACTLY as sent by the server 
stdCookie.setAttribute(ClientCookie.VERSION_ATTR, "1");
stdCookie.setAttribute(ClientCookie.DOMAIN_ATTR, ".mycompany.com");
stdCookie.setAttribute(ClientCookie.PORT_ATTR, "80,8080");
3.2. Cookie specifications
The CookieSpec interface represents a cookie management specification. The cookie management specification is expected to enforce:
rules of parsing Set-Cookie and optionally Set-Cookie2 headers.
rules of validation of parsed cookies.
formatting of Cookie header for a given host, port and path of origin.
HttpClient ships with several CookieSpec implementations:
Netscape draft:  This specification conforms to the original draft specification published by Netscape Communications. It should be avoided unless absolutely necessary for compatibility with legacy code.
Standard:  RFC 2965 HTTP state management specification.
Browser compatibility:  This implementation strives to closely mimic the (mis)behavior of common web browser applications such as Microsoft Internet Explorer and Mozilla FireFox.
Best match:  'Meta' cookie specification that picks up a cookie policy based on the format of cookies sent with the HTTP response. It basically aggregates all above implementations into one class.
Ignore cookies:  All cookies are ignored.
It is strongly recommended to use the Best Match policy and let HttpClient pick up an appropriate compliance level at runtime based on the execution context.
3.3. Choosing cookie policy
Cookie policy can be set at the HTTP client and overridden on the HTTP request level if required.
RequestConfig globalConfig = RequestConfig.custom()
        .setCookieSpec(CookieSpecs.BEST_MATCH)
        .build();
CloseableHttpClient httpclient = HttpClients.custom()
        .setDefaultRequestConfig(globalConfig)
        .build();
RequestConfig localConfig = RequestConfig.copy(globalConfig)
        .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
        .build();
HttpGet httpGet = new HttpGet("/");
httpGet.setConfig(localConfig);
3.4. Custom cookie policy
In order to implement a custom cookie policy one should create a custom implementation of the CookieSpec interface, create a CookieSpecProvider implementation to create and initialize instances of the custom specification and register the factory with HttpClient. Once the custom specification has been registered, it can be activated the same way as a standard cookie specification.
CookieSpecProvider easySpecProvider = new CookieSpecProvider() {

    public CookieSpec create(HttpContext context) {

        return new BrowserCompatSpec() {
            @Override
            public void validate(Cookie cookie, CookieOrigin origin)
                    throws MalformedCookieException {
                // Oh, I am easy
            }
        };
    }

};
Registry<CookieSpecProvider> r = RegistryBuilder.<CookieSpecProvider>create()
        .register(CookieSpecs.BEST_MATCH,
            new BestMatchSpecFactory())
        .register(CookieSpecs.BROWSER_COMPATIBILITY,
            new BrowserCompatSpecFactory())
        .register("easy", easySpecProvider)
        .build();

RequestConfig requestConfig = RequestConfig.custom()
        .setCookieSpec("easy")
        .build();

CloseableHttpClient httpclient = HttpClients.custom()
        .setDefaultCookieSpecRegistry(r)
        .setDefaultRequestConfig(requestConfig)
        .build();
3.5. Cookie persistence
HttpClient can work with any physical representation of a persistent cookie store that implements the CookieStore interface. The default CookieStore implementation called BasicCookieStore is a simple implementation backed by a java.util.ArrayList. Cookies stored in an BasicClientCookie object are lost when the container object get garbage collected. Users can provide more complex implementations if necessary.
// Create a local instance of cookie store
CookieStore cookieStore = new BasicCookieStore();
// Populate cookies if needed
BasicClientCookie cookie = new BasicClientCookie("name", "value");
cookie.setVersion(0);
cookie.setDomain(".mycompany.com");
cookie.setPath("/");
cookieStore.addCookie(cookie);
// Set the store
CloseableHttpClient httpclient = HttpClients.custom()
        .setDefaultCookieStore(cookieStore)
        .build();
3.6. HTTP state management and execution context
In the course of HTTP request execution HttpClient adds the following state management related objects to the execution context:
Lookup instance representing the actual cookie specification registry. The value of this attribute set in the local context takes precedence over the default one.
CookieSpec instance representing the actual cookie specification.
CookieOrigin instance representing the actual details of the origin server.
CookieStore instance representing the actual cookie store. The value of this attribute set in the local context takes precedence over the default one.
The local HttpContext object can be used to customize the HTTP state management context prior to request execution, or to examine its state after the request has been executed. One can also use separate execution contexts in order to implement per user (or per thread) state management. A cookie specification registry and cookie store defined in the local context will take precedence over the default ones set at the HTTP client level
CloseableHttpClient httpclient = <...>

Lookup<CookieSpecProvider> cookieSpecReg = <...>
CookieStore cookieStore = <...>

HttpClientContext context = HttpClientContext.create();
context.setCookieSpecRegistry(cookieSpecReg);
context.setCookieStore(cookieStore);
HttpGet httpget = new HttpGet("http://somehost/");
CloseableHttpResponse response1 = httpclient.execute(httpget, context);
<...>
// Cookie origin details
CookieOrigin cookieOrigin = context.getCookieOrigin();
// Cookie spec used
CookieSpec cookieSpec = context.getCookieSpec();




HttpClient httpclient = new HttpClient();

 //设置访问编码
httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
 //设置 HttpClient 接收 Cookie,用与浏览器一样的策略
 httpclient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
 //让服务器知道访问源为浏览器 
 httpclient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; rv:8.0.1) Gecko/20100101 Firefox/8.0.1");



 * 
 */
