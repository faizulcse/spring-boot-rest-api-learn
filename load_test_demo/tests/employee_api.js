import http from 'k6/http';
import { checkAndVerifyStatus, endPoints } from '../utils/index.js';

export const options = {};

export function setup() {}

export default function (data) {
  let response;
  response = http.get(
    endPoints.EMPLOYEES_PAGED.replace('{pageNumber}', '1').replace(
      '{pageSize}',
      '10'
    )
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(endPoints.EMPLOYEE_BY_ID.replace('{id}', '1'));
  checkAndVerifyStatus(response, 200);

  response = http.get(
    endPoints.EMPLOYEES_FILTER_BY_NAME.replace('{name}', 'test')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    endPoints.EMPLOYEES_FILTER_BY_NAME_AND_LOCATION.replace(
      '{name}',
      'test'
    ).replace('{location}', 'test')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    endPoints.EMPLOYEES_FILTER_BY_KEYWORD.replace('{name}', 'te')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    endPoints.EMPLOYEE_BY_NAME_AND_LOCATION.replace('{name}', 'Faizul').replace(
      '{location}',
      'Dhaka'
    )
  );
  checkAndVerifyStatus(response, 200);

  // response = http.del(
  //   endPoints.EMPLOYEE_DELETE_BY_NAME.replace('{name}', 'islam')
  // );
  // checkAndVerifyStatus(response, 20);

  // response = http.del(endPoints.EMPLOYEE_DELETE_BY_ID.replace('{id}', '1'));
  // checkAndVerifyStatus(response, 204);
}
