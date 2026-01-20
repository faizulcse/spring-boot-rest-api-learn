import http from 'k6/http';
import { checkAndVerifyStatus, ApiEndpoints, TIMEOUT } from '../utils/index.js';

export const options = {};

export function setup() {}

export default function (data) {
  let response;

  response = http.get(ApiEndpoints.API_WITH_HEADER_DATA, {
    headers: { scenario_id: '1' },
  });
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.API_WITH_PATH_DATA.replace('{scenario_id}', '1')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(ApiEndpoints.NORMAL_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(ApiEndpoints.NORMAL_SYNC_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(ApiEndpoints.FIXED_DELAY_API, { timeout: TIMEOUT });
  checkAndVerifyStatus(response, 200);

  response = http.get(ApiEndpoints.RANDOM_DELAY_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(ApiEndpoints.FIXED_DELAY_SYNC_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(ApiEndpoints.RANDOM_DELAY_SYNC_API);
  checkAndVerifyStatus(response, 200);

  response = http.post(ApiEndpoints.PRINT_API_BODY, 'ping', {
    headers: { 'Content-Type': 'text/plain' },
    timeout: TIMEOUT,
  });
  checkAndVerifyStatus(response, 200);
}
