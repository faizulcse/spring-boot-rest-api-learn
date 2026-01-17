import http from 'k6/http';
import { checkAndVerifyStatus, endPoints } from '../utils/index.js';

export const options = {
  stages: [
    { duration: '30s', target: 100 }, // ramp up to 400 users
    { duration: '1m', target: 100 }, // stay at 400 for ~4 hours
    { duration: '1m', target: 200 }, // stay at 400 for ~4 hours
    { duration: '1m', target: 300 }, // stay at 400 for ~4 hours
    { duration: '1m', target: 400 }, // stay at 400 for ~4 hours
    { duration: '30s', target: 0 }, // scale down. (optional)
  ],
};

export function setup() {}

export default function (data) {
  const response = http.get(endPoints.RANDOM_DELAY_API);
  const status = checkAndVerifyStatus(response, 200);
}
