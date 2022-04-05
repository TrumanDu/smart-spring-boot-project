import { sysLogList } from '@/services/ant-design-pro/api';
import { ProFormTextArea } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Form, Modal } from 'antd';
import moment from 'moment';
import { useRef, useState } from 'react';
import { useIntl } from 'umi';

type SysLogItem = {
  id: number;
  username: string;
  operation: string;
  method: string;
  params: string;
  ip: string;
  time: number;
  createTime: string;
};
function SysLog() {
  const intl = useIntl();
  const actionRef = useRef<ActionType>();
  const [modalVisible, handleModalVisible] = useState<boolean>(false);

  const [form] = Form.useForm();

  const handleOk = () => {
    handleModalVisible(false);
  };

  const columns: ProColumns<SysLogItem>[] = [
    {
      dataIndex: 'index',
      valueType: 'indexBorder',
      width: 48,
    },
    {
      title: 'Username',
      dataIndex: 'username',
      ellipsis: true,
      order: 10,
      width: '150px',
    },
    {
      title: 'Operation',
      dataIndex: 'operation',
      ellipsis: true,
      order: 9,
    },
    {
      title: 'Method',
      dataIndex: 'method',
      ellipsis: true,
    },
    {
      title: 'IP',
      dataIndex: 'ip',
      ellipsis: true,
      width: '150px',
    },
    {
      title: 'Cost(ms)',
      dataIndex: 'cost',
      valueType: 'digit',
      width: '150px',
      search: false,
      ellipsis: true,
    },
    {
      title: 'CreateTime',
      dataIndex: 'createTime',
      ellipsis: true,
      valueType: 'dateTimeRange',
      render: (_, record) =>
        moment(record.createTime, 'YYYY-MM-DD hh:mm:ss Z').utcOffset(0).format(),
      order: 8,
    },
    {
      title: 'Option',
      valueType: 'option',
      width: '100px',
      render: (text, record) => [
        <a
          key="editable"
          onClick={() => {
            handleModalVisible(true);
            form.setFieldsValue(record);
          }}
        >
          Detail
        </a>,
      ],
    },
  ];

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProTable<SysLogItem>
        headerTitle={intl.formatMessage({
          id: 'pages.settings.sysLog.title',
          defaultMessage: 'Log List',
        })}
        search={{
          labelWidth: 120,
        }}
        cardBordered
        actionRef={actionRef}
        rowKey={(record) => record.id}
        columns={columns}
        request={sysLogList}
        pagination={{ showSizeChanger: true, pageSize: 20 }}
      />
      <Modal title="SysLog Detail" visible={modalVisible} onOk={handleOk} onCancel={handleOk}>
        <Form form={form} layout="vertical">
          <ProFormTextArea name="method" label="Method" readonly />
          <ProFormTextArea name="params" label="Params" readonly />
        </Form>
      </Modal>
    </PageContainer>
  );
}

export default SysLog;
