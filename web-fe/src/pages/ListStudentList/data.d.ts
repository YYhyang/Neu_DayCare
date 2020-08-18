export interface StudentListItem {
  studentId: number;
  name?: string;
  parentName: string;
  address: string;
  phone: string;
  grade: number;
  registrationDate: Date;
  groupId: number;
  birthday: Date;
  ageState: Date;
  key: number;
}

export interface TableListPagination {
  total: number;
  pageSize: number;
  current: number; //todo 和后端对齐
}

export interface TableListData {
  list: StudentListItem[];
  pagination: Partial<TableListPagination>;
}

export interface StudentListParams { // 查询参数
  studentId?: string;
  ageState?: number;
  pageSize?: number;
  currentPage?: number;
  sorter?: { [key: string]: any };
}
