package com.bxtel;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class StartAddDataListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        { 
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。 
            System.out.println("\n\n\n\n\n______________\n\n\n加载了\n\n_________\n\n");
        }
        //或者下面这种方式
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext"))
        {
            System.out.println("\n\n\n_________\n\n加载一次的 \n\n ________\n\n\n\n");
        }
    }
}
