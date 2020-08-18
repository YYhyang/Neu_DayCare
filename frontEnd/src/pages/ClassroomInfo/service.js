import {request} from 'umi';

const parentURL = 'http://39.108.233.164:8080' + '/v1/students';

export async function get(id) {
  return request(parentURL + '/' + id);
}

export async function list() {
  return request(parentURL + '/list', {
    method: 'GET',
  });
}

export async function remove(id) {
  return request(parentURL + '/' + id, {
    method: 'DELETE',
  });
}

export async function update(params) {
  return request(parentURL, {
    method: 'POST',
    data: {
      ...params
    }
  });
}

export async function add(params) {
  return request(parentURL, {
    method: 'POST',
    data: {
      ...params
    }
  });
}

export async function queryStudentByAgeState(params) {
  return request(parentURL + '/queryStudentByAgeState', {
    data: {...params}
  });
}

export async function removeStudent(params) {
  return request(parentURL, {
    method: 'POST',
    data: {
      ...params
    },
  });
}
