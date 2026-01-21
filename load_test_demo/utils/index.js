import { check } from 'k6';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const TEST_ENV = __ENV.TEST_ENV || undefined;
export const API_URL = __ENV.API_URL || undefined;
export const EMAIL = __ENV.EMAIL || undefined;
export const PASSWORD = __ENV.PASSWORD;
export const TIMEOUT = __ENV.TIMEOUT || '60s';
export const DEBUG = __ENV.DEBUG || 'false';

export const DATE_REGEX = /\d{4}-\d{2}-\d{2}/g;
export const UUID_REGEX =
  /[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}/g;

Date.prototype.addDays = function (days) {
  var date = new Date(this.valueOf());
  date.setDate(date.getDate() + days);
  return date;
};

export const getDateTimeWithFormat = (addDays) => {
  var today = new Date().addDays(addDays);
  const yyyy = today.getFullYear();
  let mm = today.getMonth() + 1; // Months start at 0!
  let dd = today.getDate();
  if (dd < 10) dd = '0' + dd;
  if (mm < 10) mm = '0' + mm;
  return `${yyyy}-${mm}-${dd}`;
};

let randomDay = randomIntBetween(1, 30);
export const currentDate = getDateTimeWithFormat(1);
export const nextTwoMonthDate = getDateTimeWithFormat(60);
export const bookingCheckIn = getDateTimeWithFormat(randomDay);
export const bookingCheckOut = getDateTimeWithFormat(randomDay + 1);

export const ApiEndpoints = Object.freeze({
  APP_INFO: `${API_URL}/api/v1/app_info`,
  API_WITH_HEADER_DATA: `${API_URL}/api/v1/api_with_header_data`,
  API_WITH_PATH_DATA: `${API_URL}/api/v1/api_with_path_data/{scenario_id}`,
  NORMAL_API: `${API_URL}/api/v1/normal_api`,
  NORMAL_SYNC_API: `${API_URL}/api/v1/normal_sync_api`,
  FIXED_DELAY_API: `${API_URL}/api/v1/fixed_delay_api?milliseconds={milliseconds}`,
  RANDOM_DELAY_API: `${API_URL}/api/v1/random_delay_api?milliseconds={milliseconds}`,
  FIXED_DELAY_SYNC_API: `${API_URL}/api/v1/fixed_delay_sync_api?milliseconds={milliseconds}`,
  RANDOM_DELAY_SYNC_API: `${API_URL}/api/v1/random_delay_sync_api?milliseconds={milliseconds}`,
  PRINT_API_BODY: `${API_URL}/api/v1/print_api_body`,
  EMPLOYEES: `${API_URL}/api/v1/employees`,
  EMPLOYEES_PAGED: `${API_URL}/api/v1/employees?page_number={page_number}&page_size={page_size}`,
  EMPLOYEE_BY_ID: `${API_URL}/api/v1/employees/{id}`,
  EMPLOYEES_FILTER_BY_NAME: `${API_URL}/api/v1/employees/filter_by_name?name={name}`,
  EMPLOYEES_FILTER_BY_NAME_AND_LOCATION: `${API_URL}/api/v1/employees/filter_by_name_and_location?name={name}&location={location}`,
  EMPLOYEES_FILTER_BY_KEYWORD: `${API_URL}/api/v1/employees/filter_by_keyword?name={name}`,
  EMPLOYEE_BY_NAME_AND_LOCATION: `${API_URL}/api/v1/employees/{name}/{location}`,
  EMPLOYEE_DELETE_BY_NAME: `${API_URL}/api/v1/employees/delete/{name}`,
  EMPLOYEE_DELETE_BY_ID: `${API_URL}/api/v1/employees?id={id}`,
});

export const statusMessage = (response) => {
  return `${(response.request?.method || 'UNKNOWN').padEnd(6, ' ')}${(' ' + response.url.replaceAll(UUID_REGEX, '*****').replaceAll(DATE_REGEX, '*****') + ' ').padEnd(120, '-')}`;
};

export const verifyResponseStatus = (response, codes) => {
  return {
    [`${statusMessage(response)} status is ${codes}`]: (r) =>
      Array.isArray(codes) ? codes.includes(r.status) : r.status === codes,
  };
};

export const checkAndVerifyStatus = (response, codes) => {
  const status = check(response, verifyResponseStatus(response, codes));
  if (!status && DEBUG === 'true') {
    const statusText = `StatusText: ${response.status_text}`;
    const errorReason = response.error
      ? `ErrorReason: ${response.error}`
      : `ErrorCode: ${response.error_code}`;
    console.error(
      `${statusMessage(response)} ${response.status_text ? statusText : errorReason}`
    );
  }
  return status;
};

export const generateHtmlReport = (data, reportName = 'summary') => {
  const passRate = data.metrics.checks.values.rate * 100;
  const htmlReportName = `${reportName}_pass_rate[${passRate.toFixed(2).replace('.', '_')}].html`;
  return {
    [htmlReportName]: htmlReport(data),
    stdout: textSummary(data, { indent: ' ', enableColors: true }),
  };
};
