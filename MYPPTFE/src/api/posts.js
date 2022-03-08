import { posts, multipart, map } from './index';

function uploadPost(params) {
	return multipart.post('', params);
}

function getPost(params) {
	return posts.get(`${params}`);
}

function getFeedList(params) {
	const { userId } = params;
	return map.get(`?userId=${userId}`);
}

export { uploadPost, getPost, getFeedList };
