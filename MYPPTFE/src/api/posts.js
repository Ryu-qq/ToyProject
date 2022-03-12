import { posts, multipart } from './index';

function getPost(payload) {
	return posts.get(`${payload}`);
}

function uploadPost(payload) {
	return multipart.post('', payload);
}

function editPost(payload) {
	const postSeq = payload.get('postSeq');
	return multipart.put(`${postSeq}`, payload);
}

function deletePost(payload) {
	return posts.delete(`${payload}`);
}

export { uploadPost, getPost, deletePost, editPost };
