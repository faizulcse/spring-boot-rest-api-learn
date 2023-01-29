import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  discardResponseBodies: true,
  vus: 100,
  iterations:1000
};

export function setup() {
    console.log("=====setUp==========> ");
}

export default function(data){
    const res = http.get('https://test.sharebus.co/');
    console.log("=====execute_api====> ", res.status);
    check(res, {
        'is status 200': (r) => r.status === 200,
      });
}

export function teardown(data) {
    console.log("=====tearDown=======> ");
}