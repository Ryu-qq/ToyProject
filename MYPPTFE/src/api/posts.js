import { posts, multipart } from './index';

function uploadPost(params) {
	return multipart.post('', params);
}

function getPost(params) {
	return posts.get(`${params}`);
}

function editPost(params) {
	const postSeq = params.get('postSeq');
	return multipart.put(`${postSeq}`, params);
}

function deletePost(params) {
	return posts.delete(`${params}`);
}

export { uploadPost, getPost, deletePost, editPost };
