import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  ext: {
     loadimpact: {
        projectID: 3624712,
        name: "YOUR TEST NAME"
        }
     }
 }

 export default function () {
   http.get('https://test.k6.io');
   sleep(1);
 }