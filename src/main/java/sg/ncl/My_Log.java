//import lombok.extern.slf4j.Slf4j;
package sg.ncl;

public class My_Log {
    public String GetRemoteClientIP(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }
