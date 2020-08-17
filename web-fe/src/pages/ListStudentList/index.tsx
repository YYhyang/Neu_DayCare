import {PlusOutlined} from '@ant-design/icons';
import {Button, Divider} from 'antd';
import React, {useRef, useState} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import ProTable, {ActionType, ProColumns} from '@ant-design/pro-table';

import {StudentListItem} from './data.d';
import {list, remove, update} from "@/pages/ListStudentList/service";
import moment from 'moment';
import CreateForm from "@/pages/components/CreateForm";


import EditForm from "@/pages/components/EditForm";

const TableList: React.FC = () => {

  const formatterTime = (val: any) => {
    return val ? moment(val).format('YYYY-MM-DD HH:mm:ss') : ''
  }
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState({});
  const handleAdd = function (student: StudentListItem) {
    update(student); // todo
  }
  const handleDelete = function (row: StudentListItem) {
    remove(row.studentId); // todo 回调
  };
  const columns: ProColumns<StudentListItem>[] = [
    {
      title: 'ID',
      key: 'studentId',
      dataIndex: 'studentId',
    },
    {
      title: 'name',
      dataIndex: 'name',
      valueType: 'textarea',
    },
    {
      title: 'parentName',
      dataIndex: 'parentName',
      hideInForm: true,
    },
    {
      title: 'address',
      dataIndex: 'address',
    },
    {
      title: 'registrationDate',
      dataIndex: 'registrationDate',
      render: formatterTime
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => (
        <>
          <a
            onClick={async () => {
              await setCurrentRow(record);
              handleUpdateModalVisible(true);
            }}
          >编辑
          </a>
          <Divider type="vertical"/>
          <a onClick={() => handleDelete(record)}>删除</a>
        </>
      ),
    },
  ];

  return (
    <PageContainer>
      <ProTable<StudentListItem>
        headerTitle="查询"
        actionRef={actionRef}
        key={"studentId"}
        rowKey={"studentId"}
        toolBarRender={() => [
          <Button type="primary" onClick={() => handleModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>,
        ]}
        request={() => {
          return list();
        }}
        columns={columns}
      />
      <CreateForm onCancel={() => handleModalVisible(false)} modalVisible={createModalVisible}>
        <ProTable<StudentListItem, StudentListItem>
          onSubmit={async (value: StudentListItem) => {
            handleAdd(value); // todo 异常
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }}
          rowKey="key"
          type="form"
          columns={columns}
          rowSelection={{}}
        />
      </CreateForm>
      {currentRow && Object.keys(currentRow).length ? (
        <EditForm
          onSubmit={async (value: Partial<StudentListItem>) => {
            await update(value);
            setCurrentRow({});
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }}
          onCancel={() => {
            setCurrentRow({});
            handleUpdateModalVisible(false);
          }}
          updateModalVisible={updateModalVisible}
          values={currentRow}
        />) : null}
    </PageContainer>
  );
};

export default TableList;
