package hi.hbv601g.kritikin.services;

import java.io.IOException;

public interface NetworkManagerService {
    public String doGET(String url, Object[] args) throws IOException;
    public String doPOST(String url, Object[] args) throws IOException;
    public String doDELETE(String url, Object[] args) throws IOException;
    public String doPATCH(String url, Object[] args) throws IOException;
}
