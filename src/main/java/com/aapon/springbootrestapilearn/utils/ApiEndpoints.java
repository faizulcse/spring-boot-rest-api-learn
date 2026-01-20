package com.aapon.springbootrestapilearn.utils;

public final class ApiEndpoints {
    private ApiEndpoints() {
    }

    public static final String EMPLOYEE_BASE = "/employees";
    public static final String EMPLOYEE_BY_ID = "/employees/{id}";
    public static final String EMPLOYEE_FILTER_BY_NAME = "/employees/filter_by_name";
    public static final String EMPLOYEE_FILTER_BY_NAME_AND_LOCATION = "/employees/filter_by_name_and_location";
    public static final String EMPLOYEE_FILTER_BY_KEYWORD = "/employees/filter_by_keyword";
    public static final String EMPLOYEE_BY_NAME_AND_LOCATION = "/employees/{name}/{location}";
    public static final String EMPLOYEE_DELETE_BY_NAME = "/employees/delete/{name}";

    public static final String APP_INFO = "/app_info";
    public static final String WITH_HEADER_DATA = "/api_with_header_data";
    public static final String WITH_PATH_DATA = "/api_with_path_data/{scenario_id}";
    public static final String NORMAL = "/normal_api";
    public static final String NORMAL_SYNC = "/normal_sync_api";
    public static final String FIXED_DELAY = "/fixed_delay_api";
    public static final String RANDOM_DELAY = "/random_delay_api";
    public static final String FIXED_DELAY_SYNC = "/fixed_delay_sync_api";
    public static final String RANDOM_DELAY_SYNC = "/random_delay_sync_api";
    public static final String PRINT_BODY = "/print_api_body";

    public static final String INFLIGHT = "/internal/inflight";
}
