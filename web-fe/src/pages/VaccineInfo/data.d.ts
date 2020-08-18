export interface VaccinationItem {
  id: number;
  recordDate: Date;
  vaccinationNumber: number;
  requiredNumber: number;
  completeStatus: string;
  immunizationName: string;
  nextTime: Date;

}

export interface TableListPagination {
  total: number;
  pageSize: number;
  current: number;
}

export interface TableListData {
  list: VaccinationItem[];
  pagination: Partial<TableListPagination>;
}

export interface AddVaccinationItem {
  recordDate: Date;
  vaccinationNumber: number;
  requiredNumber: number;
  completeStatus: string;
  immunizationName: string;
  nextTime: Date;
}
