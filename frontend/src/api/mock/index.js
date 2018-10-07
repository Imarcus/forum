import posts from './data/posts'
import thread1 from './data/thread-1'
import thread2 from './data/thread-2'
import boards from './data/boards'
import users from './data/users'

const fetch = (mockData, time = 0) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(mockData)
    }, time)
  })
}

export default {
  fetchPosts () {
    return fetch(posts, 300)
  },
  fetchThreadById (id) {
    return fetch(thread1, 200)
  },
  fetchBoards () {
    return fetch(boards, 200)
  },
  fetchBoardById (id) {
    return fetch(boards.find(board => board.id === id), 200)
  },
  fetchThreadsByBoardId (id) {
    const threadIds = fetch(boards.find(board => board.id === id).threadIds)
    return this.fetchThreadsByIds(threadIds)
  },
  fetchThreadsByIds (ids) {
    return fetch([thread1, thread2], 200)
  },
  fetchThreads () {
    return fetch([thread1, thread2], 200)
  },
  createThread (thread, boardId) {
    console.log('THREAD MOCK CREATED', thread)
    return fetch({}, 200)
  },
  fetchUsers () {
    return fetch(users, 200)
  },
  login (username, password) {
    const validUser = users.find(user => user.name.toLowerCase() === username.toLowerCase() && user.password === password)
    if (validUser) {
      return fetch(validUser, 200)
    } else {
      return fetch(undefined, 200)
    }
  },
  postAnswer (username, threadId, content) {
    console.log('Answer posted to backend! Username: ', username, ', Thread ID: ', threadId, ' Content: ', content)
  },
  fetchUsersByIds (userIds) {
    return fetch(users.filter(user => userIds.includes(user.id)), 200)
  },
  putAnswerToThread (threadId, answer) {
    console.log('POSTING to thread: ' + threadId + ', with answer: ')
    thread1.answers.push(answer)
    return thread1
  }
}
