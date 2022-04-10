import {
  commonAdd,
  commonUpdate,
  commonQueryList,
  commonRemove,
  commonTreeSelectList,
} from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import ProForm, {
  ModalForm,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, message, Popconfirm } from 'antd';
import { useEffect, useImperativeHandle, useRef, useState } from 'react';
import { FormattedMessage } from 'umi';

const handleAdd = async (fields?: any) => {
  const hide = message.loading('Adding');
  try {
    hide();
    const response = await commonAdd('/api/sys_role/add', {
      roleName: fields?.roleName,
      roleCode: fields?.roleCode,
      description: fields?.description,
    });
    if (response.message == 'OK') {
      message.success('Added successfully');
    }
    return true;
  } catch (error) {
    hide();
    return false;
  }
};

const handleUserRoleAdd = async (fields?: any) => {
  const hide = message.loading('Adding');
  try {
    hide();
    const response = await commonAdd('/api/sys_role_user/add', {
      roleId: fields?.roleId,
      userId: fields.userId,
    });
    if (response.message == 'OK') {
      message.success('Added successfully');
    }
    return true;
  } catch (error) {
    hide();
    return false;
  }
};

const handleUpdate = async (fields: any) => {
  const hide = message.loading('Modifying');
  try {
    //修改
    hide();
    const response = await commonUpdate('/api/sys_role/update', {
      id: fields?.id,
      roleName: fields?.roleName,
      roleCode: fields?.roleCode,
      description: fields?.description,
    });
    if (response.message == 'OK') {
      message.success('Modify successfully');
    }
    return true;
  } catch (error) {
    hide();
    message.error('Modify failed, please try again!');
    return false;
  }
};

const handleRemove = async (id: number) => {
  const hide = message.loading('Deleting');
  try {
    await commonRemove(`/api/sys_role/delete/${id}`);
    hide();
    message.success('Deleted successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

const handleRemoveRoleUser = async (id: number) => {
  const hide = message.loading('Deleting');
  try {
    await commonRemove(`/api/sys_role_user/delete/${id}`);
    hide();
    message.success('Deleted successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

type RoleListProps = {
  onChange: (role: RoleProps) => void;
  roleChildRef: any;
  setNewRoleModalVisible: (visible: boolean) => void;
  setEditRoleModalVisible: (visible: boolean) => void;
  setRoleObj: (roleObj: RoleProps) => void;
};

function RoleList(props: RoleListProps) {
  const actionRef = useRef<ActionType>();
  const { onChange, setNewRoleModalVisible, setEditRoleModalVisible, setRoleObj, roleChildRef } =
    props;

  const roleColumns: ProColumns[] = [
    {
      title: 'RoleCode',
      key: 'roleCode',
      dataIndex: 'roleCode',
    },
    {
      title: 'RoleName',
      key: 'role',
      dataIndex: 'roleName',
    },
    {
      title: 'Option',
      key: 'option',
      width: 80,
      valueType: 'option',
      render: (text, record) => [
        <a
          key="editable"
          onClick={() => {
            setEditRoleModalVisible(true);
            setRoleObj(record);
          }}
        >
          Edit
        </a>,
        <Popconfirm
          key="delete"
          title="Are you sure to delete?"
          onConfirm={async () => {
            const success = await handleRemove(record.id);
            if (success) {
              actionRef.current?.reloadAndRest?.();
            }
          }}
          okText="Yes"
          cancelText="No"
        >
          <a href="#">Delete</a>
        </Popconfirm>,
      ],
    },
  ];

  const reloadList = () => {
    if (actionRef.current) {
      actionRef.current.reload();
    }
  };
  useImperativeHandle(roleChildRef, () => ({
    reloadList,
  }));
  return (
    <ProTable
      columns={roleColumns}
      request={() => commonQueryList('/api/sys_role/list')}
      actionRef={actionRef}
      rowKey="id"
      toolbar={{
        actions: [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              setNewRoleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ],
      }}
      options={false}
      pagination={false}
      search={false}
      onRow={(record) => {
        return {
          onClick: () => {
            if (record.id) {
              onChange(record);
            }
          },
        };
      }}
    />
  );
}

function UserList(props: any) {
  const userActionRef = useRef<ActionType>();
  const [dataSource, setDataSource] = useState([]);
  const [totalDataSource, setTotalDataSource] = useState([]);
  const { roleId, setAddUserRoleModalVisible, userChildRef } = props;

  const fetchData = async () => {
    const result = await commonQueryList(`/api/sys_role_user/list/${roleId}`);
    if (result.success) {
      setTotalDataSource(result.data);
      setDataSource(result.data);
    }
  };

  useImperativeHandle(userChildRef, () => ({
    fetchData,
  }));

  const userColumns: ProColumns[] = [
    {
      title: 'Username',
      dataIndex: 'username',
    },
    {
      title: 'Name',
      dataIndex: 'name',
    },
    {
      title: 'Option',
      key: 'option',
      width: 80,
      valueType: 'option',
      render: (text, record) => [
        <Popconfirm
          key="delete"
          title="Are you sure to delete?"
          onConfirm={async () => {
            const success = await handleRemoveRoleUser(record.id);
            if (success) {
              if (userActionRef.current) {
                userActionRef.current.reloadAndRest();
              }
              fetchData();
            }
          }}
          okText="Yes"
          cancelText="No"
        >
          <a href="#">Delete</a>
        </Popconfirm>,
      ],
    },
  ];

  useEffect(() => {
    if (roleId) {
      fetchData();
    }
  }, [roleId]);

  return (
    <ProTable
      columns={userColumns}
      dataSource={dataSource}
      pagination={{
        pageSize: 20,
        showSizeChanger: false,
      }}
      actionRef={userActionRef}
      rowKey="id"
      toolbar={{
        search: {
          onSearch: (value) => {
            const data = totalDataSource.filter((record) => {
              const name = record.name;
              const username = record.username;
              const valueLower = value.toLocaleLowerCase();
              if (
                name.toLocaleLowerCase().includes(valueLower) ||
                username.toLocaleLowerCase().includes(valueLower)
              ) {
                return true;
              }
              return false;
            });
            setDataSource(data);
          },
        },
        actions: [
          <Button
            disabled={roleId == null ? true : false}
            type="primary"
            key="primary"
            onClick={() => {
              setAddUserRoleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ],
      }}
      options={false}
      search={false}
    />
  );
}

type RoleProps = {
  id: number;
  roleName: string;
  roleCode: string;
  description: string;
};

function Role() {
  const [roleObj, setRoleObj] = useState<RoleProps>();
  const [selectRole, setSelectRole] = useState<RoleProps>();
  const [newRoleModalVisible, setNewRoleModalVisible] = useState(false);
  const [editRoleModalVisible, setEditRoleModalVisible] = useState(false);
  const [addUserRoleModalVisible, setAddUserRoleModalVisible] = useState(false);
  const roleChildRef = useRef();
  const userChildRef = useRef();

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProCard split="vertical">
        <ProCard colSpan="500px" ghost>
          <RoleList
            onChange={(role) => {
              setSelectRole(role);
            }}
            roleChildRef={roleChildRef}
            setNewRoleModalVisible={setNewRoleModalVisible}
            setEditRoleModalVisible={setEditRoleModalVisible}
            setRoleObj={setRoleObj}
          />
        </ProCard>
        <ProCard title={selectRole?.roleName}>
          <UserList
            roleId={selectRole?.id}
            setAddUserRoleModalVisible={setAddUserRoleModalVisible}
            userChildRef={userChildRef}
          />
        </ProCard>
      </ProCard>

      <ModalForm
        title="New Role"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={newRoleModalVisible}
        onVisibleChange={setNewRoleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value);
          if (success) {
            setNewRoleModalVisible(false);
            roleChildRef?.current?.reloadList();
          }
        }}
      >
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Name is required',
              },
            ]}
            width="md"
            name="roleName"
            label="RoleName"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Code is required',
              },
            ]}
            width="md"
            name="roleCode"
            label="RoleCode"
          />
        </ProForm.Group>

        <ProFormTextArea name="description" label="Description" />
      </ModalForm>

      <ModalForm
        title="Modify Role"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={editRoleModalVisible}
        onVisibleChange={setEditRoleModalVisible}
        onFinish={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            setEditRoleModalVisible(false);
            roleChildRef?.current?.reloadList();
          }
        }}
      >
        <ProFormText initialValue={roleObj?.id} name="id" hidden />
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Name is required',
              },
            ]}
            width="md"
            name="roleName"
            label="RoleName"
            initialValue={roleObj?.roleName}
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Code is required',
              },
            ]}
            width="md"
            name="roleCode"
            label="RoleCode"
            initialValue={roleObj?.roleCode}
          />
        </ProForm.Group>

        <ProFormTextArea
          name="description"
          label="Description"
          initialValue={roleObj?.description}
        />
      </ModalForm>

      <ModalForm
        title="Add User"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={addUserRoleModalVisible}
        onVisibleChange={setAddUserRoleModalVisible}
        onFinish={async (value) => {
          const success = await handleUserRoleAdd(value);
          if (success) {
            setAddUserRoleModalVisible(false);
            userChildRef?.current?.fetchData();
          }
        }}
      >
        <ProFormText initialValue={selectRole?.id} name="roleId" hidden />
        <ProFormSelect
          name="userId"
          label="User"
          request={() => commonTreeSelectList('/api/user/list/select')}
          placeholder="Please select a user"
          rules={[{ required: true, message: 'Please select user!' }]}
          fieldProps={{
            showSearch: true,
          }}
        />
      </ModalForm>
    </PageContainer>
  );
}

export default Role;
