import {request} from 'umi';
import {StudentListItem} from './data.d';


const classroomURL = 'http://39.108.233.164:8080' + '/v1/classroom';
const groupUrl = 'http://39.108.233.164:8080' + '/v1/group';


export async function get(id: number) {
  return request(classroomURL + '/' + id);
}

export async function list(id: number) {
  return request(groupUrl + '/queryByGroup/'+id, {
    method: 'GET',
  });
}

export async function remove(id: number) {
  return request(groupUrl + '/' + id, {
    method: 'DELETE',
  });
}

export async function update(student: Partial<StudentListItem>) {
  return request(groupUrl, {
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
