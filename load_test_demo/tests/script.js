import http from 'k6/http';
import { checkAndVerifyStatus, endPoints } from '../utils/index.js';

export const options = {
  vus: 1,
  iterations: 5,
};

export function setup() {}

export default function (data) {
  const response = http.get(endPoints.FIXED_DELAY_SYNC_API);
  const status = checkAndVerifyStatus(response, 200);
}
