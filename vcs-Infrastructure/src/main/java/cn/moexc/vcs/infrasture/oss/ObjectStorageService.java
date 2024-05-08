package cn.moexc.vcs.infrasture.oss;

import java.io.InputStream;
import java.io.OutputStream;

public interface ObjectStorageService {
    void write(String name, InputStream inputStream) throws Exception;
    String getUrl(String name) throws Exception;
    void read(String name, OutputStream outputStream) throws Exception;
    byte[] read(String name);
}
