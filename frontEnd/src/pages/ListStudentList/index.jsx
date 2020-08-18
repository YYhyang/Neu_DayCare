import {PlusOutlined} from '@ant-design/icons';
import {Button, Divider, message} from 'antd';
import React, {useRef, useState} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import CreateForm from './components/CreateForm';
import UpdateForm from './components/UpdateForm';
import {update} from './service';
import {list} from "@/pages/ListStudentList/service";
import moment from "moment";

/**
 * 添加节点
 * @param fields
 */

const handleAdd = async fields => {
  const hide = message.loading('正在添加');
  try {
    await add({...fields});
    hide();
    message.success('成功');
    return true;
  } catch (error) {
    hide();
    message.error('失败请重试！');
    return false;
  }
};
/**
 * 更新节点
 * @param fields
 */

const handleUpdate = async fields => {
  const hide = message.loading('正在配置');

  try {
    await update({
      parentName: fields.parentName,
    });
    hide();
    message.success('成功');
    return true;
  } catch (error) {
    hide();
    message.error('失败请重试！');
    return false;
  }
};
/**
 *  删除节点
 * @param selectedRows
 */

const formatterTime = (val) => {
  return val ? moment(val).format('YYYY-MM-DD HH:mm:ss') : ''
}

const handleRemove = async selectedRows => {
  const hide = message.loading('正在删除');
  if (!selectedRows) return true;

  try {
    await remove({
      key: selectedRows.map(row => row.key),
    });
    hide();
    message.success('删除成功，即将刷新');
    return true;
  } catch (error) {
    hide();
    message.error('删除失败，请重试');
    return false;
  }
};

const TableList = () => {
  const [createModalVisible, handleModalVisible] = useState(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState(false);
  const [stepFormValues, setStepFormValues] = useState({});
  const actionRef = useRef();
  const [selectedRowsState, setSelectedRows] = useState([]);
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
      title: 'address',
      dataIndex: 'address',
    },
    {
      title: 'birthday',
      dataIndex: 'birthday',
      render: formatterTime
    },
    {
      title: 'Contact Number',
      dataIndex: 'phone',
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
    <PageContainer>
      <ProTable
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
        request={(params, sorter, filter) => list({...params, sorter, filter})}
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
