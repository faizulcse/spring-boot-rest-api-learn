import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  scenarios: {
      example_scenario_1: {
        executor: 'shared-iterations',

        // common scenario configuration
        startTime: '10s',
        gracefulStop: '5s',

        // executor-specific configuration
        vus: 10,
        iterations: 200
      },
       example_scenario_2: {
        executor: 'shared-iterations',

        // common scenario configuration
        startTime: '10s',
        gracefulStop: '5s',

        // executor-specific configuration
        vus: 10,
        iterations: 200
       }
  }
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