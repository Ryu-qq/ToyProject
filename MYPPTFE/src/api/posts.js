import { posts, multipart } from './index';

function uploadPost(params) {
	return multipart.post('', params);
}

function getPost(params) {
	return posts.get(`${params}`);
}

export { uploadPost, getPost };
