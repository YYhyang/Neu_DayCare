import {request} from 'umi';

const parentURL = 'http://localhost:8080' + '/v1/groups';

export async function get(id) {
  return request(parentURL + '/' + id);
}

export async function list(id) {
  return request(parentURL + '/list/' + id);
}
