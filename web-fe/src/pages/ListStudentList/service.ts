import {request} from 'umi';
import {StudentListItem} from './data.d';


const parentURL = 'http://39.108.233.164:8080' + '/v1/students';


export async function get(id: number) {
  return request(parentURL + '/' + id);
}

export async function list() {
  return request(parentURL + '/list', {
    method: 'GET',
  });
}

export async function remove(id: number) {
  return request(parentURL + '/' + id, {
    method: 'DELETE',
  });
}

export async function update(student: Partial<StudentListItem>) {
  return request(parentURL, {
    method: 'POST',
    data: {
      ...student
    }
  });
}

export async function add(student: Partial<StudentListItem>) {
  return request(parentURL, {
    method: 'POST',
    data: {
      ...student
    }
  });
}

export async function queryStudentByAgeState(params: { ageState: number }) {
  return request(parentURL + '/queryStudentByAgeState', {
    params,
  });
}

export async function removeStudent(params: { key: number[] }) {
  return request(parentURL, {
    method: 'POST',
    data: {
      ...params
    },
  });
}
