import {PlusOutlined} from '@ant-design/icons';
import {Button, Divider} from 'antd';
import React, {useRef, useState} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import CreateForm from './components/CreateForm';
import UpdateForm from './components/UpdateForm';
import {list} from "@/pages/GroupInfo/service";
import Header from "@/pages/GroupInfo/components/PageHeader";

const TableList = () => {
  const [createModalVisible, handleModalVisible] = useState(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState(false);
  const [stepFormValues, setStepFormValues] = useState({});
  const actionRef = useRef();
  const [, setSelectedRows] = useState([]);
  const id = '1';
  const columns = [
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
      title: 'Parent\'s Name',
      dataIndex: 'parentName',
      hideInForm: true,
    },
    {
      title: 'Group Number',
      dataIndex: 'groupId',
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
          <a onClick={() => handleRemove(record)}>删除</a>
        </>
      ),
    },
  ];
  return (
    <PageContainer
      pageHeaderRender={() => {
        return (
          <Header groupId={id}/>
        );
      }}>
      <ProTable
        search={false}
        headerTitle="查询表格"
        actionRef={actionRef}
        rowKey={(record) => {
          return record.studentId;
        }}
        toolBarRender={() => [
          <Button type="primary" onClick={() => handleModalVisible(true)}>
            <PlusOutlined/> 新建
          </Button>,
        ]}
        request={(params, sorter, filter) => list(66)}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => setSelectedRows(selectedRows),
        }}
      />
      <CreateForm onCancel={() => handleModalVisible(false)} modalVisible={createModalVisible}>
        <ProTable
          onSubmit={async value => {
            const success = await handleAdd(value);
            if (success) {
              handleModalVisible(false);
              if (actionRef.current) {
                actionRef.current.reload();
              }
            }
          }}
          rowKey={(record) => {
            return record.studentId;
          }}
          type="form"
          columns={columns}
          rowSelection={{}}
        />
      </CreateForm>
      {stepFormValues && Object.keys(stepFormValues).length ? (
        <UpdateForm
          onSubmit={async value => {
            const success = await handleUpdate(value);

            if (success) {
              handleUpdateModalVisible(false);
              setStepFormValues({});

              if (actionRef.current) {
                actionRef.current.reload();
              }
            }
          }}
          onCancel={() => {
            handleUpdateModalVisible(false);
            setStepFormValues({});
          }}
          updateModalVisible={updateModalVisible}
          values={stepFormValues}
        />
      ) : null}
    </PageContainer>
  );
};

export default TableList;
