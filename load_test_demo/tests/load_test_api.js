import http from 'k6/http';
import { checkAndVerifyStatus, ApiEndpoints, TIMEOUT } from '../utils/index.js';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

const getDelayInSeconds = () => {
  return randomIntBetween(1, 10) * 1000;
};

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

  response = http.get(
    ApiEndpoints.FIXED_DELAY_API.replace('{milliseconds}', getDelayInSeconds()),
    { timeout: TIMEOUT }
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.RANDOM_DELAY_API.replace(
      '{milliseconds}',
      getDelayInSeconds()
    ),
    { timeout: TIMEOUT }
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.FIXED_DELAY_SYNC_API.replace(
      '{milliseconds}',
      getDelayInSeconds()
    ),
    { timeout: TIMEOUT }
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.RANDOM_DELAY_SYNC_API.replace(
      '{milliseconds}',
      getDelayInSeconds()
    ),
    { timeout: TIMEOUT }
  );
  checkAndVerifyStatus(response, 200);

  response = http.post(
    ApiEndpoints.PRINT_BODY_API,
    JSON.stringify({
      name: 'Faizul',
      location: 'Dhaka',
    }),
    {
      timeout: TIMEOUT,
    }
  );
  checkAndVerifyStatus(response, 200);
}
