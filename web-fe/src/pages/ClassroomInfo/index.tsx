import {PlusOutlined} from '@ant-design/icons';
import {Button, Divider, message} from 'antd';
import React, {useRef, useState} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import ProTable, {ActionType, ProColumns} from '@ant-design/pro-table';

import {GroupInfo} from './data.d';
import {list, remove, update, add} from "@/pages/GroupInfo/service";
import moment from 'moment';
import CreateForm from "@/pages/components/CreateForm";


import EditForm from "@/pages/components/EditForm";
// import {addRule} from "@/pages/GroupInfo/service";
// import {TableListItem} from "@/pages/GroupInfo/data";

const TableList: React.FC = () => {

  const formatterTime = (val: any) => {
    return val ? moment(val).format('YYYY-MM-DD HH:mm:ss') : ''
  }
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState({});
  const classroomId;

  const handleAdd = async (group: GroupInfo) => {
    const hide = message.loading('Adding');
    try {
      await add({...group});
      hide();
      message.success('Succeed!');
      return true;
    } catch (error) {
      hide();
      message.error('Please retry!');
      return false;
    }

  };


  const handleDelete = function (row: GroupInfo) {
    remove(row.groupId); // todo 回调
  };
  const columns: ProColumns<GroupInfo>[] = [
  //   groupId: number;
  // classroomId: number;
  // ageStage: string;
  // fullState: string;
  // teacherId: number;
  // studentCount: number;
  // ratio: number;
    {
      title: 'ID',
      key: 'groupId',
      dataIndex: 'groupId',
    },
    {
      title: 'Classroom Id',
      dataIndex: 'classroomId',
    },
    {
      title: 'Age Stage',
      dataIndex: 'ageState',
      hideInForm: true,
    },
    {
      title: 'Full State',
      dataIndex: 'fullState',
    },
    {
      title: 'Teacher Id',
      dataIndex: 'teacherId',
      render: formatterTime
    },
    {
      title: 'Student Count Number',
      dataIndex: 'studentCount',
    },
    {
      title: 'Group Number',
      dataIndex: 'groupId',
    },
    {
      title: 'Ratio',
      dataIndex: 'ratio',
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
      <ProTable<GroupInfo>
        headerTitle="Search by Group Ids"
        actionRef={actionRef}
        key={"groupId"}
        rowKey={"groupId"}
        toolBarRender={() => [
          <Button type="primary" onClick={() => handleModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>,
        ]}
        request={() => {
          return list(1);
        }}
        columns={columns}
      />
      <CreateForm onCancel={() => handleModalVisible(false)} modalVisible={createModalVisible}>
        <ProTable<GroupInfo, GroupInfo>
          onSubmit={async (value: GroupInfo) => {
            const success = await handleAdd(value);
            if (success) {
              handleModalVisible(false);
              if (actionRef.current) {
                actionRef.current.reload();
              }
            }
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }}
          rowKey="key"
          type="form"
          columns={GroupInfo}
          rowSelection={{}}
        />
      </CreateForm>
      {currentRow && Object.keys(currentRow).length ? (
        <EditForm
          onSubmit={async (value: Partial<GroupInfo>) => {
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
