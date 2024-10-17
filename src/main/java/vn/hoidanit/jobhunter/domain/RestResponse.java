package vn.hoidanit.jobhunter.domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

public class RestResponse<T> {
  private int statusCode;
  private String error;

  // message có thể là String, hoặc là ArrayList
  private Object message;
  private T data;

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Object getMessage() {
    return message;
  }

  public void setMessage(Object message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
