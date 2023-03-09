package hi.hbv601g.kritikin.services;

public interface NetworkManagerService {
    public String doGET(String url, Object[] args[]);
    public String doPOST(String url, Object[] args[]);
    public String doDELETE(String url, Object[] args[]);
    public String doPatch(String url, Object[] args[]);
}
