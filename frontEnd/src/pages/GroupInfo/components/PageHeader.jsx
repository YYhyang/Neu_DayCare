import {Descriptions, InputNumber, PageHeader} from 'antd';
import {get} from '../service';
import React from "react";

export default class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {groupId: props.groupId};
    this.handleChange(props.groupId);
  }

  componentDidMount() {
    let res = get(this.state.groupId);
    for (var i in res.data) {
      this.setState({i: res[i]});
    }
  }

  handleChange = (value) => {
    this.state.groupId = value;
    get(this.state.groupId).then(res => {
      this.setState(res.data);
    });
  };


  render() {
    return (
      <PageHeader title="Group Manangement">
        <Descriptions size="small" column={3}>
          <Descriptions.Item label="groupId"><InputNumber min={1} defaultValue={66}
                                                          onChange={this.handleChange}/></Descriptions.Item>
          <Descriptions.Item
            label="classroomId">{this.state.classroomId ? this.state.classroomId : ''}</Descriptions.Item>
          <Descriptions.Item label="ageState">{this.state.ageState ? this.state.ageState : ''}</Descriptions.Item>
          <Descriptions.Item label="teacherId">{this.state.teacherId ? this.state.teacherId : ''}</Descriptions.Item>
          <Descriptions.Item
            label="studentCount">{this.state.studentCount ? this.state.studentCount : ''}</Descriptions.Item>
          <Descriptions.Item label="ratio">{this.state.ratio ? this.state.ratio : ''}</Descriptions.Item>
          <Descriptions.Item label="fullState">{this.state.fullState ? this.state.fullState : ''}</Descriptions.Item>
        </Descriptions>
      </PageHeader>
    );
  }
}
