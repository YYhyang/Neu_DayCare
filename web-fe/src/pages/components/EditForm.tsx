import React, {useState} from 'react';
import {Button, Form, Input, Modal} from 'antd';
import {StudentListItem} from "@/pages/ListStudentList/data";
import FormItem from "antd/es/form/FormItem";

export interface EditFormProps {
  updateModalVisible: boolean;
  onSubmit: (values: FormValueType) => void;
  onCancel: (flag?: boolean, formVals?: FormValueType) => void;
  values: Partial<StudentListItem>;
}


export interface FormValueType extends Partial<StudentListItem> {
  studentId?: number;
  parentName?: string;
  address?: string;
}


const EditForm: React.FC<EditFormProps> = (props) => {
  const [formVals, setFormVals] = useState<FormValueType>({
    studentId: props.values.studentId,
    parentName: props.values.parentName,
    address: props.values.address
  });

  const [form] = Form.useForm();

  const {
    onSubmit: handleUpdate,
    onCancel: handleUpdateModalVisible,
    updateModalVisible,
    values,
  } = props;

  const check = async () => {
    const fieldsValue = await form.validateFields();
    console.log(formVals);
    console.log(fieldsValue);
    console.log('------');
    await setFormVals({...formVals, ...fieldsValue});
    console.log(formVals);
    console.log(fieldsValue);
    handleUpdate({...fieldsValue});
  };

  const formLayout = {
    labelCol: {span: 7},
    wrapperCol: {span: 13},
  };

  return (
    <Modal
      destroyOnClose
      title="编辑"
      visible={updateModalVisible}
      footer={null}
      onCancel={() => handleUpdateModalVisible()}
    >
      <Form  {...formLayout} form={form} initialValues={{
        studentId: formVals.studentId,
        parentName: formVals.parentName,
        address: formVals.address
      }}>
        <FormItem label='学生ID' name='studentId'><Input value={formVals.studentId}/></FormItem>
        <FormItem label='地址' name='address'><Input value={formVals.address}/></FormItem>
        <FormItem label='家长名' name='parentName'><Input value={formVals.parentName}/></FormItem>
        <Button type="primary" onClick={() => check()}>
          提交
        </Button>

        <Button type="primary" onClick={() => handleUpdateModalVisible(false, values)}>
          取消
        </Button>
      </Form>
    </Modal>
  );
};
export default EditForm;
