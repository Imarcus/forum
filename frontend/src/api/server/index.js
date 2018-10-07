import axios from 'axios'

export default {
  fetchPosts () {
    return axios
      .get('/api/hello')
      .then(response => response.data)
  },
  fetchBoards () {
    return axios
      .get('api/boards')
      .then(response => response.data)
  },
  fetchBoardsById (boardId) {
    return axios
      .get('api/boards/' + boardId)
      .then(response => response.data)
  },
  createBoard (boardName) {
    return axios
      .post('api/boards', {
        name: boardName
      })
      .then(response => response.data)
  },
  fetchThreads () {
    return axios
      .get('api/threads')
      .then(response => response.data)
  },
  fetchThreadById (threadId) {
    return axios
      .get('api/threads/' + threadId)
      .then(response => response.data)
  },
  createThread (thread, boardId) {
    return axios
      .post('api/threads?boardId=' + boardId, thread)
      .then(response => response.data)
  },
  putAnswerToThread (threadId, answer) {
    return axios
      .patch('api/threads/' + threadId, answer)
      .then(response => response.data)
  },
  fetchThreadsByIds (threadIdArray) {
    return axios
      .post('api/threads/ids', threadIdArray)
      .then(response => response.data)
  },
  fetchThreadsByBoardId (boardId) {
    return axios
      .get('api/threads/boardId/' + boardId)
      .then(response => response.data)
  },
  fetchUsers () {
    return axios
      .get('api/users')
      .then(response => response.data)
  },
  fetchUserById (userId) {
    return axios
      .get('api/users/' + userId)
      .then(response => response.data)
  },
  fetchUsersByIds (userIds) {
    return axios
    .post('api/users/id', userIds)
    .then(response => response.data)
  },
  createUser (user) {
    return axios
      .post('api/users', user)
      .then(response => response.data)
  },
  login (username, password) {
    return axios
      .get('api/login?username=' + username + '&password=' + password)
      .then(response => response.data)
  }
}
