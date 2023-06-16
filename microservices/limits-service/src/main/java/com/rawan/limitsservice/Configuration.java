package com.rawan.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;  
import org.springframework.stereotype.Component;  
@Component  
@ConfigurationProperties("limits-service")  
public class Configuration   
{  
private int maximum;  
private int minimum;  
private int average;
public void setMaximum(int maximum)   
{  
this.maximum = maximum;  
}  
public void setMinimum(int minimum)   
{  
this.minimum = minimum;  
} 
public void setAverage(int average)
{
    this.average = average;
} 
public int getMaximum()   
{  
return maximum;  
}  
public int getMinimum()   
{  
return minimum;  
}  
public int getAverage() 
{
    return average;
}
}
