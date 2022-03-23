// const BACKEND_DOMAINPORT = process.env.VUE_APP_API_URL;
// const FRONTEND_DOMAINPORT = process.env.VUE_APP_API_URL;
const BACKEND_PORT = process.env.BACKEND_PORT === null ? '' : `:8080`;
const BACKEND_URL = `${location.protocol}//${location.hostname}${BACKEND_PORT}`;
const FRONTEND_PORT = process.env.FRONTEND_PORT === null ? '' : `:3000`;
const REDIRECT_URI = `${location.protocol}//${location.hostname}${FRONTEND_PORT}/oauth/redirect`;

export default {
	getSocialLoginUrl(socialType) {
		return `${BACKEND_URL}/oauth2/authorization/${socialType}?redirect_uri=${REDIRECT_URI}`;
	},
	getSocialImage(socialType) {
		switch (socialType) {
			case 'google':
				return '@/assets/btn_google_signin_light_pressed_web@2x.png';
		}
	},
	clickOutside(exceptionId = '#') {
		return {
			bind: function (el, binding, vnode) {
				el.clickOutsideEvent = function (e) {
					if (
						!(el === e.target || el.contains(e.target)) &&
						e.target.id !== exceptionId
					) {
						vnode.context[binding.expression](e);
					}
				};
				document.body.addEventListener('click', el.clickOutsideEvent);
			},
			unbind: function (el) {
				document.body.removeEventListener('click', el.clickOutsideEvent);
			},
		};
	},
};
