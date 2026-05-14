# API Mapping

| REST API | Java Sync | Java Async |
| --- | --- | --- |
| `POST /projects/{appId}/join` | `startAgent` | `startAgentAsync` |
| `POST /projects/{appId}/agents/{agentId}/leave` | `stopAgent` | `stopAgentAsync` |
| `GET /projects/{appId}/agents/{agentId}` | `getAgent` | `getAgentAsync` |
| `GET /projects/{appId}/agents` | `listAgents` | `listAgentsAsync` |
| `POST /projects/{appId}/agents/{agentId}/update` | `updateAgent` | `updateAgentAsync` |
| `POST /projects/{appId}/agents/{agentId}/speak` | `speak` | `speakAsync` |
| `POST /projects/{appId}/agents/{agentId}/interrupt` | `interrupt` | `interruptAsync` |
| `GET /projects/{appId}/agents/{agentId}/history` | `getHistory` | `getHistoryAsync` |
| `POST /projects/{appId}/agents/{agentId}/think` | `think` | `thinkAsync` |
| `GET /projects/{appId}/agents/{agentId}/turns` | `getTurns` | `getTurnsAsync` |
