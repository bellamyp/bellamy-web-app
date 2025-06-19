export const BackendConfig = {
  springApiUrl: getSpringApiUrl()
};

function getSpringApiUrl(): string {
  return window.location.hostname === 'localhost'
    ? 'http://localhost:8080'
    : 'http://Bellamy-web-spring2-env.eba-phpfvh9s.us-east-2.elasticbeanstalk.com';
}
