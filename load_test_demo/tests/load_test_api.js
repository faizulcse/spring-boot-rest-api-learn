import http from 'k6/http';
import { checkAndVerifyStatus, endPoints } from '../utils/index.js';

export const options = {};

export function setup() {}

export default function (data) {
  let response;
  response = http.get(endPoints.API1);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.API2);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.API3);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.API4);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.API5);
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.API_WITH_HEADER_DATA, {
    headers: { scenarioId: '1' },
  });
  checkAndVerifyStatus(response, 200);

  response = http.get(
    endPoints.API_WITH_PATH_DATA.replace('{scenarioId}', '1')
  );
  checkAndVerifyStatus(response, 200);
}
