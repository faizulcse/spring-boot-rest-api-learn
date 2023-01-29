import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  discardResponseBodies: true,
  vus: 1,
  iterations: 1000,
  duration: '5m'
};

export function setup() {
    console.log("=====setUp==========> ");
}

export default function(data){
    const res = http.get('https://test.sharebus.co/');
    console.log("=====res_status=====> ", res.status_text);
    check(res, {'is status 200': (r) => r.status === 200});
}

export function teardown(data) {
    console.log("=====tearDown=======> ");
}