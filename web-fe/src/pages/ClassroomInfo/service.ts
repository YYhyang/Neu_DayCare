import {request} from 'umi';
import {GroupInfo, StudentListItem} from './data.d';


const classroomURL = 'http://39.108.233.164:8080' + '/v1/classroom';
const groupUrl = 'http://39.108.233.164:8080' + '/v1/group';


export async function get(id: number) {
  return request(classroomURL + '/' + id);
}

export async function list(id: number) {
  return request(groupUrl + '/lists/'+id, {
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

export async function add(group: Partial<GroupInfo>) {
  return request(groupUrl, {
    method: 'POST',
    data: {
      ...group
    }
  });
}


