package com.happy.delivery.presentation.common.response;

/**
 * ApiResponse.
 */
public class ApiResponse<T> {

  private final boolean success;
  private final T data;
  private final Error error;

  /**
   * ApiResponse Constructor.
   */
  public ApiResponse(boolean success, T data, Error error) {
    this.success = success;
    this.data = data;
    this.error = error;
  }

  public static <T> ApiResponse success(T data) {
    return new ApiResponse(true, data, null);
  }

  public static <T> ApiResponse fail(String code, String message) {
    return new ApiResponse(false, null, new Error(code, message));
  }

  /**
   * Error.
   */
  public static class Error {

    private final String code;
    private final String message;

    public Error(String code, String message) {
      this.code = code;
      this.message = message;
    }

    public String getCode() {
      return code;
    }

    public String getMessage() {
      return message;
    }
  }

  public boolean isSuccess() {
    return success;
  }

  public T getData() {
    return data;
  }

  public Error getError() {
    return error;
  }
}
