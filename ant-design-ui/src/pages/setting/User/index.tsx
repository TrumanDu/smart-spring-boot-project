import { useState, useRef } from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { useIntl, FormattedMessage } from 'umi';
import { Button, message, Popconfirm } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import ProForm, { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { userList, addUser, updateUser, removeUser } from '@/services/ant-design-pro/api';

type UserItem = {
  id: number;
  username: string;
  password: string;
  name: string;
  email: string;
  description: string;
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
    const response = await addUser({ ...fields });
    if (response.message == 'OK') {
      message.success('Added successfully');
    }
    return true;
  } catch (error) {
    hide();
    return false;
  }
};

function User() {
  const intl = useIntl();
  const actionRef = useRef<ActionType>();

  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [modifyUser, setModifyUser] = useState<UserItem>();

  const handleUpdate = async (fields: any) => {
    const hide = message.loading('Modifying');
    try {
      //修改
      hide();
      const response = await updateUser({ ...fields });
      if (response.message == 'OK') {
        message.success('Modify successfully');
        actionRef.current?.reloadAndRest?.();
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
        actionRef.current?.reloadAndRest?.();
      }
      return true;
    } catch (error) {
      hide();
      message.error('Delete failed, please try again');
      return false;
    }
  };

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
          onConfirm={() => {
            handleRemove(record.id);
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
