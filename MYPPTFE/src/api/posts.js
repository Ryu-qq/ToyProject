import { post, userinfo } from './index';

function uploadPost(params) {
	return post.post('', params);
}

function getPostList(params) {
	return userinfo.get(`'/${params}'`);
}

function getPost(params) {
	const { endpoint } = params;

	return post.get(`'/${endpoint}'`);
}

export { uploadPost, getPostList, getPost };
