import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

export const errorRate = new Rate('errors');

export const options = {
  stages: [
    { duration: '1m', target: 50 }, // simulate ramp-up of traffic from 1 to 100 users over 5 minutes.
    { duration: '2m', target: 50 }, // stay at 100 users for 10 minutes
    { duration: '1m', target: 0 }, // ramp-down to 0 users
  ],
};


export default function () {
  const url = 'https://test.sharebus.co/';
  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  check(http.get(url, params), {
    'status is 200': (r) => r.status == 200,
  }) || errorRate.add(1);
  sleep(1);
}
