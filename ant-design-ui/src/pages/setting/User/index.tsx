import { useState, useRef } from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { useIntl, FormattedMessage } from 'umi';
import { Button, message, Popconfirm } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import ProForm, {
  ModalForm,
  ProFormText,
  ProFormTextArea,
  ProFormTreeSelect,
} from '@ant-design/pro-form';
import {
  userList,
  addUser,
  updateUser,
  removeUser,
  commonTreeSelectList,
} from '@/services/ant-design-pro/api';

type SysOrg = {
  id: number;
  orgName: string;
};

type UserItem = {
  id: number;
  username: string;
  password: string;
  name: string;
  email: string;
  description: string;
  sysOrgVO: SysOrg;
};

/**
 * @en-US Add node
 * @zh-CN 添加节点
 * @param fields
 */
const handleAdd = async (fields?: any) => {
  const hide = message.loading('Adding');
  try {
    hide();
    let user = {};
    if (fields.orgId) {
      user = {
        orgId: fields.orgId.value,
        username: fields.username,
        password: fields.password,
        name: fields.name,
        email: fields.email,
        description: fields.description,
      };
    } else {
      user = {
        username: fields.username,
        password: fields.password,
        name: fields.name,
        email: fields.email,
        description: fields.description,
      };
    }
    const response = await addUser(user);
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
    let user = {};
    if (fields.orgId) {
      user = {
        orgId: fields.orgId.value,
        id: fields.id,
        username: fields.username,
        password: fields.password,
        name: fields.name,
        email: fields.email,
        description: fields.description,
      };
    } else {
      user = {
        id: fields.id,
        username: fields.username,
        password: fields.password,
        name: fields.name,
        email: fields.email,
        description: fields.description,
      };
    }
    const response = await updateUser(user);
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
    // 删除
    hide();
    const response = await removeUser(id);
    if (response.message == 'OK') {
      message.success('Deleted successfully');
    }
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

function User() {
  const intl = useIntl();
  const actionRef = useRef<ActionType>();

  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [modifyUser, setModifyUser] = useState<UserItem>();

  const columns: ProColumns<UserItem>[] = [
    {
      dataIndex: 'index',
      valueType: 'indexBorder',
      width: 48,
    },
    {
      title: 'Username',
      dataIndex: 'username',
      ellipsis: true,
    },
    {
      title: 'Name',
      dataIndex: 'name',
      ellipsis: true,
    },
    {
      title: 'Email',
      dataIndex: 'email',
      ellipsis: true,
    },
    {
      title: 'Org',
      dataIndex: 'orgName',
      renderText: (text, record) => (record.sysOrgVO != undefined ? record.sysOrgVO.orgName : '-'),
      ellipsis: true,
      search: false,
    },
    {
      title: 'Description',
      dataIndex: 'description',
      ellipsis: true,
      search: false,
      valueType: 'textarea',
    },
    {
      title: 'Option',
      valueType: 'option',
      key: 'option',
      render: (text, record) => [
        <a
          key="editable"
          onClick={() => {
            handleUpdateModalVisible(true);
            setModifyUser(record);
          }}
        >
          Edit
        </a>,
        <Popconfirm
          key="delete"
          title="Are you sure to delete this user?"
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

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProTable<UserItem>
        headerTitle={intl.formatMessage({
          id: 'pages.settings.user.title',
          defaultMessage: 'User List',
        })}
        search={{
          labelWidth: 120,
        }}
        cardBordered
        actionRef={actionRef}
        rowKey={(record) => record.id}
        columns={columns}
        request={userList}
        pagination={{ showSizeChanger: true, pageSize: 10 }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ]}
      />

      <ModalForm
        title={intl.formatMessage({
          id: 'pages.settings.user.add',
          defaultMessage: 'New user',
        })}
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'User name is required',
              },
            ]}
            width="md"
            name="username"
            label="Username"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Password is required',
              },
            ]}
            width="md"
            name="password"
            label="Password"
          />
        </ProForm.Group>

        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Name is required',
              },
            ]}
            width="md"
            name="name"
            label="Name"
          />
          <ProFormText width="md" name="email" label="Email" />
        </ProForm.Group>

        <ProFormTreeSelect
          name="orgId"
          label="Org"
          placeholder="Please select"
          allowClear
          secondary
          request={() => commonTreeSelectList('/api/sys_org/list/tree_select')}
          // tree-select args
          fieldProps={{
            treeLine: true,
            showArrow: false,
            filterTreeNode: true,
            showSearch: true,
            dropdownMatchSelectWidth: false,
            labelInValue: true,
            autoClearSearchValue: true,
            treeNodeFilterProp: 'title',
            fieldNames: {
              label: 'title',
            },
          }}
        />

        <ProFormTextArea name="description" label="Description" />
      </ModalForm>

      <ModalForm
        title={intl.formatMessage({
          id: 'pages.settings.user.modify',
          defaultMessage: 'Modify user',
        })}
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={updateModalVisible}
        onVisibleChange={handleUpdateModalVisible}
        onFinish={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText initialValue={modifyUser?.id} name="id" hidden />
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'User name is required',
              },
            ]}
            width="md"
            initialValue={modifyUser?.username}
            name="username"
            label="Username"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Password is required',
              },
            ]}
            width="md"
            name="password"
            label="Password"
          />
        </ProForm.Group>

        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Name is required',
              },
            ]}
            width="md"
            name="name"
            label="Name"
            initialValue={modifyUser?.name}
          />
          <ProFormText width="md" name="email" label="Email" initialValue={modifyUser?.email} />
        </ProForm.Group>

        <ProFormTreeSelect
          name="orgId"
          label="Org"
          placeholder="Please select"
          allowClear
          secondary
          request={() => commonTreeSelectList('/api/sys_org/list/tree_select')}
          // tree-select args
          fieldProps={{
            defaultValue: modifyUser?.sysOrgVO?.id,
            treeLine: true,
            showArrow: false,
            filterTreeNode: true,
            showSearch: true,
            dropdownMatchSelectWidth: false,
            labelInValue: true,
            autoClearSearchValue: true,
            treeNodeFilterProp: 'title',
            fieldNames: {
              label: 'title',
            },
          }}
        />

        <ProFormTextArea
          name="description"
          label="Description"
          initialValue={modifyUser?.description}
        />
      </ModalForm>
    </PageContainer>
  );
}

export default User;
