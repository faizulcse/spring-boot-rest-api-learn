import http from 'k6/http';
import { checkAndVerifyStatus, endPoints, TIMEOUT } from '../utils/index.js';

export const options = {};

export function setup() {}

export default function (data) {
  let response;
  response = http.get(endPoints.NORMAL_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.NORMAL_SYNC_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.FIXED_DELAY_API, { timeout: TIMEOUT });
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.RANDOM_DELAY_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.FIXED_DELAY_SYNC_API);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.RANDOM_DELAY_SYNC_API);
  checkAndVerifyStatus(response, 200);

  response = http.post(endPoints.PRINT_API_BODY, 'ping', {
    headers: { 'Content-Type': 'text/plain' },
    timeout: TIMEOUT,
  });
  checkAndVerifyStatus(response, 200);
}
