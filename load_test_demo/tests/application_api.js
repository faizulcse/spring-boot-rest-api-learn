import http from 'k6/http';
import { checkAndVerifyStatus, endPoints } from '../utils/index.js';

export const options = {};

export function setup() {}

export default function (data) {
  const response = http.get(endPoints.APP_INFO);
  checkAndVerifyStatus(response, 200);
}
