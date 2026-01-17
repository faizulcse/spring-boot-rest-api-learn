import http from 'k6/http';
import { checkAndVerifyStatus, endPoints } from '../utils/index.js';

export const options = {
  vus: 100,
  iterations: 100,
};

export function setup() { }

export default function (data) {
  const response = http.get(endPoints.APP_INFO);
  const status = checkAndVerifyStatus(response, 200);
}
