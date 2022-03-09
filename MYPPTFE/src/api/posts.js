import { posts, multipart, map } from './index';

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

function getFeedList(params) {
	const { userId } = params;
	return map.get(`?userId=${userId}`);
}

export { uploadPost, getPost, getFeedList, deletePost, editPost };
