import http from 'k6/http';
import { checkAndVerifyStatus, ApiEndpoints } from '../utils/index.js';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

function randomFromArray(values) {
  return values[randomIntBetween(0, values.length - 1)];
}

function randomEmail() {
  return `${randomString(8)}.${randomString(6)}@example.com`;
}

export const options = {};

export function setup() {}

export default function (data) {
  let response;
  response = http.get(
    ApiEndpoints.EMPLOYEES_PAGED.replace('{page_number}', '0').replace(
      '{page_size}',
      '10'
    )
  );
  checkAndVerifyStatus(response, 200);

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  response = http.post(
    ApiEndpoints.EMPLOYEES,
    JSON.stringify({
      name: randomString(6),
      age: randomIntBetween(18, 60),
      location: randomFromArray(['Dhaka', 'Chittagong', 'Sylhet', 'Khulna']),
      email: randomEmail(),
      department: randomFromArray(['Engineering', 'Sales', 'HR', 'Finance']),
    }),
    params
  );
  checkAndVerifyStatus(response, 201);

  response = http.get(ApiEndpoints.EMPLOYEE_BY_ID.replace('{id}', '1'));
  checkAndVerifyStatus(response, 200);

  response = http.put(
    ApiEndpoints.EMPLOYEE_BY_ID.replace('{id}', '1'),
    JSON.stringify({
      name: randomString(6),
      age: randomIntBetween(18, 60),
      location: randomFromArray(['Dhaka', 'Chittagong', 'Sylhet', 'Khulna']),
      email: randomEmail(),
      department: randomFromArray(['Engineering', 'Sales', 'HR', 'Finance']),
    }),
    params
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.EMPLOYEES_FILTER_BY_NAME.replace('{name}', 'test')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.EMPLOYEES_FILTER_BY_NAME_AND_LOCATION.replace(
      '{name}',
      'test'
    ).replace('{location}', 'test')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.EMPLOYEES_FILTER_BY_KEYWORD.replace('{name}', 'te')
  );
  checkAndVerifyStatus(response, 200);

  response = http.get(
    ApiEndpoints.EMPLOYEE_BY_NAME_AND_LOCATION.replace(
      '{name}',
      'Faizul'
    ).replace('{location}', 'Dhaka')
  );
  checkAndVerifyStatus(response, 200);

  // response = http.del(
  //   ApiEndpoints.EMPLOYEE_DELETE_BY_NAME.replace('{name}', 'islam')
  // );
  // checkAndVerifyStatus(response, 204);

  // response = http.del(ApiEndpoints.EMPLOYEE_DELETE_BY_ID.replace('{id}', '1'));
  // checkAndVerifyStatus(response, 204);
}
