import {request} from 'umi';
import {AddVaccinationItem, VaccinationItem} from './data.d';


const vaccinationUrl = 'http://39.108.233.164:8080' + '/vaccination';
// const studentUrl = 'http://39.108.233.164:8080' + '/v1/group';


export async function get(id: number) {
  return request(vaccinationUrl + '/' + id);
}

export async function queryByStudentId(id: number) {
  return request(vaccinationUrl + '/' + id);
}

export async function list(id: number) {
  return request(vaccinationUrl + '/listByStudentId/'+id, {
    method: 'GET',
  });
}

export async function remove(id: number) {
  return request(vaccinationUrl + '/' + id, {
    method: 'DELETE',
  });
}

export async function update(student: Partial<VaccinationItem>) {
  return request(vaccinationUrl, {
    method: 'POST',
    data: {
      ...student
    }
  });
}

export async function add(group: Partial<VaccinationItem>) {
  return request(vaccinationUrl, {
    method: 'POST',
    data: {
      ...group
    }
  });
}


