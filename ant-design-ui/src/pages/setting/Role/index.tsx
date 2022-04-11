import { commonAdd, commonUpdate, commonTreeSelectList } from '@/services/ant-design-pro/api';
import ProCard from '@ant-design/pro-card';
import ProForm, {
  ModalForm,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import { message } from 'antd';
import { useRef, useState } from 'react';

import RoleList from './RoleList';
import RoleMenu from './RoleMenu';
import UserList from './UserList';

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

function Role() {
  const [roleObj, setRoleObj] = useState<RoleProps>();
  const [selectRole, setSelectRole] = useState<RoleProps>();
  const [newRoleModalVisible, setNewRoleModalVisible] = useState(false);
  const [editRoleModalVisible, setEditRoleModalVisible] = useState(false);
  const [addUserRoleModalVisible, setAddUserRoleModalVisible] = useState(false);
  const [tab, setTab] = useState('userTag');
  const roleChildRef = useRef();
  const userChildRef = useRef();
  const menuChildRef = useRef();

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProCard split="vertical">
        <ProCard colSpan="30%" title="Role List" headerBordered>
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
        <ProCard
          title={selectRole ? 'Role: ' + selectRole?.roleName : 'Please select a role'}
          tabs={{
            activeKey: tab,
            onChange: (key) => {
              setTab(key);
            },
          }}
        >
          <ProCard.TabPane key="userTag" tab="Member Allocation">
            <UserList
              roleId={selectRole?.id}
              setAddUserRoleModalVisible={setAddUserRoleModalVisible}
              userChildRef={userChildRef}
            />
          </ProCard.TabPane>
          <ProCard.TabPane key="menuTag" tab="Menu Permission">
            <RoleMenu roleId={selectRole?.id} menuChildRef={menuChildRef} />
          </ProCard.TabPane>
          <ProCard.TabPane key="dataTag" tab="Data Permission">
            TODO
          </ProCard.TabPane>
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
        title="Member Allocation"
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
